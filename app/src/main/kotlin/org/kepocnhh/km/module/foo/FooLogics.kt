package org.kepocnhh.km.module.foo

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import org.kepocnhh.km.entity.Foo
import org.kepocnhh.km.module.app.Injection
import sp.kx.logics.Logics
import java.util.UUID
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.minutes

internal class FooLogics(
    private val injection: Injection,
) : Logics(injection.contexts.main) {
    data class State(
        val items: List<Foo>,
    )

    private val _state = MutableStateFlow<State?>(null)
    val state = _state.asStateFlow()

    fun requestState() = launch {
        val items = withContext(injection.contexts.default) {
            injection.locals.foo
        }
        _state.value = State(items = items)
    }

    fun add() = launch {
        withContext(injection.contexts.default) {
            val value = Foo(
                id = UUID.randomUUID(),
                created = System.currentTimeMillis().milliseconds,
                text = "foo:" + System.nanoTime() % 1024,
            )
            val items = injection.locals.foo + value
            injection.locals.foo = items.sortedBy { it.created }
        }
        val items = withContext(injection.contexts.default) {
            injection.locals.foo
        }
        _state.value = State(items = items)
    }

    fun delete(id: UUID) = launch {
        withContext(injection.contexts.default) {
            val items = injection.locals.foo.toMutableList()
            for (i in items.indices) {
                val item = items[i]
                if (item.id == id) {
                    items.removeAt(i)
                    break
                }
            }
            injection.locals.foo = items
        }
        val items = withContext(injection.contexts.default) {
            injection.locals.foo
        }
        _state.value = State(items = items)
    }

    fun update(id: UUID) = launch {
        withContext(injection.contexts.default) {
            val items = injection.locals.foo.toMutableList()
            for (i in items.indices) {
                val item = items[i]
                if (item.id == id) {
                    items.removeAt(i)
                    items += item.copy(text = "foo:" + System.nanoTime() % 1024)
                    break
                }
            }
            injection.locals.foo = items.sortedBy { it.created }
        }
        val items = withContext(injection.contexts.default) {
            injection.locals.foo
        }
        _state.value = State(items = items)
    }
}
