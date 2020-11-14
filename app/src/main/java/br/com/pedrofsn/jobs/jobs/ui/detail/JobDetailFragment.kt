package br.com.pedrofsn.jobs.jobs.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import br.com.pedrofsn.jobs.R
import br.com.pedrofsn.jobs.domain.BaseFragment

/*
    CREATED BY @PEDROFSN IN 10/11/20 22:57
*/

class JobDetailFragment(override val layout: Int = R.layout.fragment_job_detail) : BaseFragment() {

    private val arguments: JobDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val job = arguments.job
        Toast.makeText(requireContext(), job.title + " <<< ", Toast.LENGTH_SHORT).show()
    }

}
