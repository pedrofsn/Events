package br.com.pedrofsn.jobs.di

import br.com.pedrofsn.jobs.domain.network.APIConnection
import br.com.pedrofsn.jobs.features.list.JobsRepository
import br.com.pedrofsn.jobs.features.list.JobsRepositoryImpl
import br.com.pedrofsn.jobs.features.list.JobsViewModel
import br.com.pedrofsn.jobs.features.list.RemoteDataSource
import br.com.pedrofsn.jobs.features.list.RemoteDataSourceImpl
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val jobsModule = module {
    single { APIConnection.service }
    factory<RemoteDataSource> { RemoteDataSourceImpl(api = get()) }
    factory<JobsRepository> { JobsRepositoryImpl(dataSource = get()) }
    viewModel { JobsViewModel(repository = get()) }
}