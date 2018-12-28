package br.com.ufg.www.events.view.event.jobs

import br.com.redcode.base.extensions.delay
import br.com.redcode.base.extensions.extract
import br.com.redcode.base.mvvm.extensions.observer
import br.com.redcode.base.utils.Constants
import br.com.redcode.easyrecyclerview.library.extension_functions.setCustomAdapter
import br.com.redcode.easyrestful.library.fragment.FragmentSwipeRefreshRecyclerViewMVVM
import br.com.ufg.www.events.R
import br.com.ufg.www.events.data.ui.LabelEvents
import br.com.ufg.www.events.databinding.FragmentJobsBinding
import br.com.ufg.www.events.view.event.list.adapter.AdapterEvent

class JobsFragment : FragmentSwipeRefreshRecyclerViewMVVM<FragmentJobsBinding, JobsViewModel>() {

    override val colummns: Int = 3
    override val hasSearch: Boolean = true
    override val hasSwipeRefreshLayout: Boolean = true
    override val adapter = AdapterEvent(null)
    override val classViewModel: Class<JobsViewModel> = JobsViewModel::class.java
    override val layout: Int = R.layout.fragment_jobs

    private val observer = observer<LabelEvents> { updateUI(it) }

    override fun afterOnCreate() {
        super.afterOnCreate()
        binding.recyclerView.setCustomAdapter(adapter)
        load()
    }

    private fun load() {
        showProgress()
        delay(Constants.ONE_SECOND_IN_MILLISECONDS * 5) { viewModel.load() }
    }

    override fun setupUI() {
        super.setupUI()
        viewModel.liveData.observe(this, observer)
    }

    private fun updateUI(label: LabelEvents) {
        adapter.setCustomList(label.items)
        hideProgress()
    }

    override fun onRefreshed() {
        super.onRefreshed()
        delay {
            showMessage("onRefreshed")
            hideProgress()
        }
    }

    override fun search(query: String?) {
        super.search(query)
        showMessage(extract safe query)
    }

}