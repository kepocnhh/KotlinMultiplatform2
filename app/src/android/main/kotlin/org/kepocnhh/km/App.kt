package org.kepocnhh.km

import android.app.Application

internal class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Env.create()
        // todo
    }
}
