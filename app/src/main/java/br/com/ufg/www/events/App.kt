package br.com.ufg.www.events

import android.app.Application
import android.content.Context
import br.com.redcode.base.BuildConfig
import timber.log.Timber
import java.lang.ref.WeakReference

class App : Application() {

    companion object {
        private lateinit var mContext: WeakReference<Context>
        fun getContext() = mContext.get()

        var userLoggedIn: String = "" // it isn't the better approach, but it can solve for us right now
    }

    override fun onCreate() {
        super.onCreate()
        mContext = WeakReference(this)
        initializeTimber()
    }

    private fun initializeTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

}
