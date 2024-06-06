package org.kepocnhh.km

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import kotlinx.coroutines.Dispatchers
import org.kepocnhh.km.entity.Foo
import org.kepocnhh.km.module.app.Injection
import org.kepocnhh.km.provider.Contexts
import org.kepocnhh.km.provider.Locals
import sp.kx.logics.Logics
import sp.kx.logics.LogicsFactory
import sp.kx.logics.LogicsProvider
import sp.kx.logics.contains
import sp.kx.logics.get
import sp.kx.logics.remove
import java.util.concurrent.atomic.AtomicBoolean

internal object Env {
    private val _created = AtomicBoolean(false)
    private var _injection: Injection? = null
    private val _logicsProvider = LogicsProvider(
        factory = object : LogicsFactory {
            override fun <T : Logics> create(type: Class<T>): T {
                val injection = checkNotNull(_injection) { "No injection!" }
                return type
                    .getConstructor(Injection::class.java)
                    .newInstance(injection)
            }
        },
    )

    val injection: Injection get() = checkNotNull(_injection) { "No injection!" }

    fun create(injection: Injection) {
        if (!_created.compareAndSet(false, true)) TODO()
        _injection = injection
    }

    @Composable
    inline fun <reified T : Logics> logics(label: String = T::class.java.name): T {
        val (contains, logics) = synchronized(Env::class.java) {
            remember { _logicsProvider.contains<T>(label = label) } to _logicsProvider.get<T>(label = label)
        }
        DisposableEffect(Unit) {
            onDispose {
                if (!contains) _logicsProvider.remove<T>(label = label)
            }
        }
        return logics
    }
}
