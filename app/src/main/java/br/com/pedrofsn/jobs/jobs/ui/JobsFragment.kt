package br.com.pedrofsn.jobs.jobs.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.pedrofsn.jobs.R
import br.com.pedrofsn.jobs.jobs.ui.list.JobAdapter
import org.koin.android.viewmodel.ext.android.viewModel

/*
    CREATED BY @PEDROFSN IN 24/10/20 12:08
*/

class JobsFragment : Fragment() {

    private val recyclerView by lazy { view?.findViewById<RecyclerView>(R.id.recyclerView) }

    private val viewModel: JobsViewModel by viewModel()

    private val adapter = JobAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView?.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter.setCustomList(viewModel.getJobs())
    }

}