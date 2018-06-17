package br.com.ufg.www.places

import android.app.Application

class App : Application() {

    companion object {
        lateinit var instance: App
        var userLoggedIn: String = "" // it isn't the better approach, but it can solve for us right now
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
