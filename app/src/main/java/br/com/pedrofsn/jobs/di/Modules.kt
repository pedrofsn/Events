package br.com.pedrofsn.jobs.di

import br.com.pedrofsn.jobs.jobs.data.repository.Repository
import br.com.pedrofsn.jobs.jobs.data.repository.RepositoryImpl
import org.koin.dsl.module

/*
    CREATED BY @PEDROFSN IN 24/10/20 12:30
*/

val jobsModule = module {
    single<Repository> { RepositoryImpl() }
}