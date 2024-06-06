package org.kepocnhh.km

import android.app.Application
import kotlinx.coroutines.Dispatchers
import org.kepocnhh.km.module.app.Injection
import org.kepocnhh.km.provider.Contexts
import org.kepocnhh.km.provider.FinalLocals

internal class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Env.create(
            injection = Injection(
                contexts = Contexts(
                    main = Dispatchers.Main,
                    default = Dispatchers.Default,
                ),
                locals = FinalLocals(context = this),
            ),
        )
    }
}
