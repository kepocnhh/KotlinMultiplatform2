package org.kepocnhh.km

import java.util.concurrent.atomic.AtomicBoolean

internal object Env {
    private val created = AtomicBoolean(false)

    private fun onCreate() {
        // todo
    }

    fun create() {
        if (!created.compareAndSet(false, true)) TODO()
        onCreate()
    }
}
