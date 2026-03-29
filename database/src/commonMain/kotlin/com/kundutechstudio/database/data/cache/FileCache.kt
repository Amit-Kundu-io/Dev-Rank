package com.kundutechstudio.database.data.cache

expect class FileCache {
    fun save(fileName: String, data: String)
    fun read(fileName: String): String?
    fun clear(fileName: String)
}