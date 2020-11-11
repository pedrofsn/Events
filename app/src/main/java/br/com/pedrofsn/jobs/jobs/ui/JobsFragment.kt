package br.com.pedrofsn.jobs.jobs.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.pedrofsn.jobs.R
import br.com.pedrofsn.jobs.domain.BaseFragment
import br.com.pedrofsn.jobs.jobs.ui.list.JobAdapter
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.viewmodel.ext.android.viewModel

/*
    CREATED BY @PEDROFSN IN 24/10/20 12:08
*/

class JobsFragment : BaseFragment() {

    private val viewModel: JobsViewModel by viewModel()
    override val layout = R.layout.fragment_main

    private val adapter = JobAdapter { item, _ ->
        Toast.makeText(requireContext(), item.title, Toast.LENGTH_SHORT).show()
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