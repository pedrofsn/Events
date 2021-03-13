package br.com.pedrofsn.jobs.domain.pagination

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import br.com.pedrofsn.network.domain.Constants.EMPTY_STRING
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private const val PAGINATION_FIRST_PAGE = 0
private const val ITENS_PER_PAGE = 20

class Pagination<T>(
    private val scope: CoroutineScope,
    private var filter: (() -> String?)? = null,

    private val onPreExecute: () -> Unit,
    private val doInBackground: suspend (String?, Int) -> (Paginated<T>)?,
    private val onPostExecute: (List<T>, Any?) -> Unit,
    private val onSucces: () -> Unit,
    private val onError: () -> Unit,
    private val handleEmptyData: ((Boolean) -> Unit)? = null,

    private var count: Int = 0,
    private var total: Int = 0,
    private var started: Boolean = false
) {

    var dataSource: MyDataSource? = null
    private var lastFilter: String? = EMPTY_STRING
    val data: LiveData<PagedList<T>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(ITENS_PER_PAGE)
            .setEnablePlaceholders(false)
            .build()

        data = initializedPagedListBuilder(config).build()
    }

    fun invalidate() {
        total = 0
        count = 0
        started = false
        lastFilter = null
        dataSource?.invalidate()
    }

    private fun initializedPagedListBuilder(config: PagedList.Config): LivePagedListBuilder<Int, T> {

        val dataSourceFactory = object : DataSource.Factory<Int, T>() {
            override fun create(): Pagination<T>.MyDataSource {
                val filterCurrent = filter?.invoke()
                dataSource = MyDataSource(filter = filterCurrent)
                return dataSource as MyDataSource
            }
        }

        return LivePagedListBuilder<Int, T>(dataSourceFactory, config)
    }

    inner class MyDataSource(val filter: String?) : PageKeyedDataSource<Int, T>() {

        override fun loadInitial(
            loadInitialParams: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, T>
        ) {
            paginatedLoad(PAGINATION_FIRST_PAGE) { _, list ->
                callback.onResult(list, null, 1)
            }
        }

        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
            val page = params.key + 1
            paginatedLoad(params.key) { _, list -> callback.onResult(list, page) }
        }

        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) = Unit

        private fun paginatedLoad(page: Int, onLoaded: (Int, List<T>) -> Unit) {
            if (hasMoreToLoad(page)) {
                if (started.not()) {
                    started = true
                }

                onPreExecute.invoke()
                scope.launch {
                    doInBackground.invoke(filter, page)?.let { result ->

                        total = result.totalInAllPages
                        count += result.data.size

                        onPostExecute.invoke(result.data, result)
                        handleEmptyData?.invoke(total == 0)

                        onLoaded(total, result.data)
                        lastFilter = filter
                        onSucces.invoke()
                    } ?: onError.invoke()
                }
            }
        }

        private fun hasMoreToLoad(page: Int): Boolean {
            val isValidPage = page >= PAGINATION_FIRST_PAGE
            val firstCase = started.not() && total == 0 && count == 0
            val hasMore = count < total || firstCase
            val hasChangedFilter = lastFilter.isNullOrBlank() || lastFilter != filter
            return isValidPage && hasMore && hasChangedFilter
        }
    }
}