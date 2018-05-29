package br.com.ufg.www.events

import android.app.Application
import android.content.Context

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
