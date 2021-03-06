package br.com.pedrofsn.jobs.features.list

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import br.com.pedrofsn.jobs.R
import br.com.pedrofsn.jobs.domain.view.BaseFragment
import br.com.pedrofsn.jobs.features.list.adapter.JobAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class JobsFragment(override val layout: Int = R.layout.fragment_jobs) : BaseFragment(),
    SwipeRefreshLayout.OnRefreshListener, JobsView {

    private val viewModel: JobsViewModel by viewModel()
    private var swipeRefreshLayout: SwipeRefreshLayout? = null

    private val adapter = JobAdapter { item, _ ->
        val directions = JobsFragmentDirections.openDetail(item)
        findNavController().navigate(directions)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeDataChanges()
        observeProgressChanges()
    }

    override fun observeProgressChanges() {
        viewModel.loading.observe(this) { isLoading ->
            if (isLoading) showRefresh()
            else hideRefresh()
        }
    }

    override fun observeDataChanges() {
        viewModel.jobs.observe(this) { data ->
            adapter.setCustomList(data)
        }
    }

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
    }

    override fun onResume() {
        super.onResume()
        onRefresh()
    }

    override fun onRefresh() = load()

    override fun load() = viewModel.load()

    override fun hideRefresh() {
        activity?.runOnUiThread { swipeRefreshLayout?.isRefreshing = false }
    }

    override fun showRefresh() {
        activity?.runOnUiThread { swipeRefreshLayout?.isRefreshing = false }
    }

    override fun onDestroyView() {
        swipeRefreshLayout?.setOnRefreshListener(null)
        super.onDestroyView()
    }
}