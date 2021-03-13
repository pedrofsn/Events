package br.com.pedrofsn.jobs.features.list

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import br.com.pedrofsn.jobs.R
import br.com.pedrofsn.jobs.data.model.JobItem
import br.com.pedrofsn.jobs.domain.extensions.observer
import br.com.pedrofsn.jobs.domain.extensions.toLogcat
import br.com.pedrofsn.jobs.domain.view.BaseFragmentMVVM
import br.com.pedrofsn.jobs.features.list.adapter.JobAdapter
import br.com.pedrofsn.network.model.ErrorHandled
import org.koin.android.viewmodel.ext.android.viewModel

class JobsFragment(override val layout: Int = R.layout.fragment_jobs) : BaseFragmentMVVM(),
    SwipeRefreshLayout.OnRefreshListener {

    override val viewModel: JobsViewModel by viewModel()

    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var textViewError: TextView? = null
    private var recyclerView: RecyclerView? = null

    private val observerPagination = observer<PagedList<JobItem>> { onPaged(it) }

    private val adapter = JobAdapter { item, _ ->
        val directions = JobsFragmentDirections.openDetail(item)
        findNavController().navigate(directions)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeDataChanges()
        observeProgressChanges()
        observeErrorChanges()
    }

    private fun observeProgressChanges() = viewModel.loading.observe(this) { isLoading ->
        when {
            isLoading -> showRefresh()
            else -> hideRefresh()
        }
    }

    private fun observeErrorChanges() = viewModel.error.observe(this) { isError ->
        when {
            isError -> showError()
            else -> showSuccess()
        }
    }

    private fun observeDataChanges() = viewModel.pagination.data.observe(this, observerPagination)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        setupSwipeRefreshLayout()
        setupRecyclerView()
    }

    private fun initViews(view: View) {
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
        textViewError = view.findViewById(R.id.textViewError)
        recyclerView = view.findViewById(R.id.recyclerView)
    }

    private fun setupSwipeRefreshLayout() {
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

    private fun setupRecyclerView() {
        recyclerView?.adapter = adapter

        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        recyclerView?.addItemDecoration(divider)
    }

    private fun onPaged(pagedList: PagedList<JobItem>) {
        adapter.submitList(pagedList)
    }

    override fun onRefresh() {
        viewModel.pagination.invalidate()
    }

    private fun hideRefresh() {
        activity?.runOnUiThread { swipeRefreshLayout?.isRefreshing = false }
    }

    private fun showRefresh() {
        activity?.runOnUiThread { swipeRefreshLayout?.isRefreshing = true }
    }

    override fun onDestroyView() {
        swipeRefreshLayout?.setOnRefreshListener(null)
        super.onDestroyView()
    }

    override fun onServerNotResponding() {
        showError(getString(R.string.network_error_server_not_responding))
    }

    override fun onUnknownHost() {
        showError(getString(R.string.network_error_unknown_host_excpetion))
    }

    override fun onNetworkHttpError(errorHandled: ErrorHandled) {
        showError(getString(R.string.network_error_http_x, errorHandled.statusCode))
    }

    override fun onNetworkTimeout() {
        showError(getString(R.string.network_error_timeout))
    }

    override fun onNetworkUnknownError(message: String) {
        showError(getString(R.string.network_error_unknown))
        message.toLogcat()
    }

    private fun showError(messageError: String) {
        textViewError?.text = messageError
    }

    private fun showError() {
        textViewError?.visibility = View.VISIBLE
        recyclerView?.visibility = View.GONE
    }

    private fun showSuccess() {
        textViewError?.text = null
        textViewError?.visibility = View.GONE
        recyclerView?.visibility = View.VISIBLE
    }
}