package br.com.pedrofsn.jobs.jobs.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import br.com.pedrofsn.jobs.R
import br.com.pedrofsn.jobs.databinding.FragmentJobDetailBinding
import br.com.pedrofsn.jobs.domain.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class JobDetailFragment(override val layout: Int = R.layout.fragment_job_detail) : BaseFragment() {

    private val viewModel: JobViewModel by viewModel()
    private val arguments: JobDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentJobDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val job = arguments.job
        viewModel.data.postValue(job)
        Toast.makeText(requireContext(), job.title + " <<< ", Toast.LENGTH_SHORT).show()
    }

}