package br.com.pedrofsn.jobs.features.list

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import br.com.pedrofsn.jobs.R
import br.com.pedrofsn.jobs.data.model.JobItem
import br.com.pedrofsn.jobs.domain.extensions.observer
import br.com.pedrofsn.jobs.domain.view.BaseFragment
import br.com.pedrofsn.jobs.features.list.adapter.JobAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class JobsFragment(override val layout: Int = R.layout.fragment_jobs) : BaseFragment(),
    SwipeRefreshLayout.OnRefreshListener {

    private val viewModel: JobsViewModel by viewModel()
    private var swipeRefreshLayout: SwipeRefreshLayout? = null

    private val observerPagination = observer<PagedList<JobItem>> { onPaged(it) }

    private val adapter = JobAdapter { item, _ ->
        val directions = JobsFragmentDirections.openDetail(item)
        findNavController().navigate(directions)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeDataChanges()
        observeProgressChanges()
    }

    private fun observeProgressChanges() = viewModel.loading.observe(this) { isLoading ->
        when {
            isLoading -> showRefresh()
            else -> hideRefresh()
        }
    }

    private fun observeDataChanges() = viewModel.pagination.data.observe(this, observerPagination)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSwipeRefreshLayout(view)
        setupRecyclerView(view)
    }

    private fun setupSwipeRefreshLayout(view: View) {
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout?.apply {
            setOnRefreshListener(this@JobsFragment)
            setColorSchemeResources(
                R.color.design_default_color_primary,
                R.color.design_default_color_primary_dark,
                R.color.design_default_color_primary,
                R.color.design_default_color_primary_dark
            )
        }
    }

    private fun setupRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter

        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(divider)
    }

    private fun onPaged(pagedList: PagedList<JobItem>) = adapter.submitList(pagedList)

    override fun onRefresh() = viewModel.pagination.invalidate()

    private fun hideRefresh() {
        activity?.runOnUiThread { swipeRefreshLayout?.isRefreshing = false }
    }

    private fun showRefresh() {
        activity?.runOnUiThread { swipeRefreshLayout?.isRefreshing = false }
    }

    override fun onDestroyView() {
        swipeRefreshLayout?.setOnRefreshListener(null)
        super.onDestroyView()
    }
}