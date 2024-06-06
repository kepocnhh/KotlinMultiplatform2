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
//            injection.locals.foo // todo
            (1..40).map { number ->
                Foo(
                    id = UUID.randomUUID(),
                    created = System.currentTimeMillis().milliseconds - 1.hours + number.minutes,
                    text = "text/number/$number"
                )
            }
        }
        _state.value = State(items = items)
    }
}
