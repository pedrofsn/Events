package br.com.pedrofsn.jobs.jobs.ui.list

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.pedrofsn.jobs.R
import br.com.pedrofsn.jobs.domain.BaseFragment
import br.com.pedrofsn.jobs.jobs.ui.list.adapter.JobAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class JobsFragment(override val layout: Int = R.layout.fragment_jobs) : BaseFragment() {

    private val viewModel: JobsViewModel by viewModel()

    private val adapter = JobAdapter { item, _ ->
        val directions = JobsFragmentDirections.openDetail(item)
        findNavController().navigate(directions)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.jobs.observe(this) { data ->
            adapter.setCustomList(data)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews(view)
    }

    private fun initializeViews(view: View) {
        view.findViewById<RecyclerView>(R.id.recyclerView).run {
            this.adapter = adapter
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.initialize()
    }
}