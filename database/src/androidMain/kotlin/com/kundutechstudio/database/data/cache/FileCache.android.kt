package com.kundutechstudio.database.data.cache

import android.content.Context

actual class FileCache(private val context: Context) {

    actual fun save(fileName: String, data: String) {
        context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
            it.write(data.toByteArray())
        }
    }

    actual fun read(fileName: String): String? {
        return try {
            context.openFileInput(fileName).bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            null
        }
    }

    actual fun clear(fileName: String) {
        context.deleteFile(fileName)
    }
}