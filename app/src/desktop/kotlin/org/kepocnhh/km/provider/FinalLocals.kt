package org.kepocnhh.km.provider

import org.kepocnhh.km.entity.Foo
import java.io.File
import java.util.UUID
import kotlin.time.Duration.Companion.milliseconds

internal class FinalLocals(
    private val root: File,
) : Locals {
    init {
        if (!root.exists()) {
            check(root.mkdirs())
        } else if (!root.isDirectory) {
            check(root.mkdirs())
        }
    }

    override var foo: List<Foo>
        get() {
            val file = root.resolve("foo.txt")
            if (!file.exists()) return emptyList()
            val lines = file.readLines()
            val size = lines[0].toInt()
            val items = mutableListOf<Foo>()
//            var index = 1
//            for (i in 0 until size) {
//                items += Foo(
//                    id = UUID.fromString(lines[index++]),
//                    created = lines[index++].toLong().milliseconds,
//                    text = lines[index++],
//                )
//            }
//            return items
            return (0 until size).map { index ->
                val j = index * 3
                Foo(
                    id = UUID.fromString(lines[j + 1]),
                    created = lines[j + 2].toLong().milliseconds,
                    text = lines[j + 3],
                )
            }
        }
        set(value) {
            val file = root.resolve("foo.txt")
            val builder = StringBuilder()
            builder.append(value.size)
            value.forEach {
                builder
                    .append("\n")
                    .append(it.id.toString())
                    .append("\n")
                    .append(it.created.inWholeMilliseconds.toString())
                    .append("\n")
                    .append(it.text)
            }
            file.writeText(builder.toString())
        }
}
