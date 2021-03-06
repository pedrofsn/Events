package br.com.pedrofsn.jobs.di

import br.com.pedrofsn.jobs.domain.network.APIConnection
import br.com.pedrofsn.jobs.features.list.Interactor
import br.com.pedrofsn.jobs.features.list.InteractorImpl
import br.com.pedrofsn.jobs.features.list.JobsRepository
import br.com.pedrofsn.jobs.features.list.JobsRepositoryImpl
import br.com.pedrofsn.jobs.features.list.JobsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val jobsModule = module {
    single { APIConnection.service }
    factory<Interactor> { InteractorImpl(api = get()) }
    factory<JobsRepository> { JobsRepositoryImpl(interactor = get()) }
    viewModel { JobsViewModel(repository = get()) }
}