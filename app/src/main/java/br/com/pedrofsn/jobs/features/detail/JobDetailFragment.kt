package br.com.pedrofsn.jobs.features.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.com.pedrofsn.jobs.data.model.JobItem
import br.com.pedrofsn.jobs.databinding.FragmentJobDetailBinding

class JobDetailFragment : Fragment() {

    private val job: JobItem by lazy {
        val arguments: JobDetailFragmentArgs by navArgs()
        return@lazy arguments.job
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentJobDetailBinding.inflate(inflater)
        binding.data = job
        return binding.root
    }
}