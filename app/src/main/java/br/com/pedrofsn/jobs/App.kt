package br.com.pedrofsn.jobs

import android.app.Application
import br.com.pedrofsn.jobs.di.jobsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/*
    CREATED BY @PEDROFSN IN 24/10/20 12:28
*/

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(jobsModule)
        }
    }

}