package br.com.pedrofsn.jobs.jobs.ui.list

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.pedrofsn.jobs.R
import br.com.pedrofsn.jobs.domain.BaseFragment
import br.com.pedrofsn.jobs.jobs.data.repository.Repository
import br.com.pedrofsn.jobs.jobs.ui.list.adapter.JobAdapter
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

/*
    CREATED BY @PEDROFSN IN 24/10/20 12:08
*/

class JobsFragment(override val layout: Int = R.layout.fragment_main) : BaseFragment() {

    private val viewModel: JobsViewModel by viewModel()
    private val recyclerView by lazy { view?.findViewById<RecyclerView>(R.id.recyclerView) }

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
        recyclerView?.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.initialize()
    }
}