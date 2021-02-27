package br.com.pedrofsn.jobs.di

import br.com.pedrofsn.jobs.jobs.data.repository.Repository
import br.com.pedrofsn.jobs.jobs.data.repository.RepositoryImpl
import br.com.pedrofsn.jobs.jobs.ui.list.JobsViewModel
import br.com.pedrofsn.jobs.network.API
import br.com.pedrofsn.jobs.network.APIConnection
import br.com.pedrofsn.jobs.network.InteractorImpl
import br.com.pedrofsn.jobs.network.infra.Interactor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/*
    CREATED BY @PEDROFSN IN 24/10/20 12:30
*/

val jobsModule = module {
    single<API> { APIConnection.service }
    factory<Interactor> { InteractorImpl(api = get()) }
    factory<Repository> { RepositoryImpl(interactor = get()) }
    viewModel { JobsViewModel(repository = get()) }
}