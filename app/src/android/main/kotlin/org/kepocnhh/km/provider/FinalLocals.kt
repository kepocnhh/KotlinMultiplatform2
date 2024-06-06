package org.kepocnhh.km.provider

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import org.kepocnhh.km.BuildConfig
import org.kepocnhh.km.entity.Foo
import java.util.UUID
import kotlin.time.Duration.Companion.milliseconds

internal class FinalLocals(
    context: Context,
) : Locals {
    private val prefs = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)

    override var foo: List<Foo>
        get() {
            val json = prefs.getString("foo", null) ?: return emptyList()
            val array = JSONArray(json)
            return (0 until array.length()).map { index ->
                val obj = array.getJSONObject(index)
                Foo(
                    id = UUID.fromString(obj.getString("id")),
                    created = obj.getLong("created").milliseconds,
                    text = obj.getString("text"),
                )
            }
        }
        set(value) {
            val array = JSONArray()
            value.forEach {
                val obj = JSONObject()
                    .put("id", it.id.toString())
                    .put("created", it.created.inWholeMilliseconds)
                    .put("text", it.text)
                array.put(obj)
            }
            prefs.edit()
                .putString("foo", array.toString())
                .commit()
        }
}
