package com.kundutechstudio.database.data.cache

import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.*

import platform.Foundation.*

actual class FileCache {

    private fun getPath(fileName: String): String {
        val dir = NSSearchPathForDirectoriesInDomains(
            NSDocumentDirectory,
            NSUserDomainMask,
            true
        ).first() as String

        return "$dir/$fileName"
    }

    @OptIn(ExperimentalForeignApi::class)
    actual fun save(fileName: String, data: String) {
        val path = getPath(fileName)

        val nsString = data as NSString

        nsString.writeToFile(
            path,
            atomically = true,
            encoding = NSUTF8StringEncoding,
            error = null
        )
    }

    @OptIn(ExperimentalForeignApi::class)
    actual fun read(fileName: String): String? {
        val path = getPath(fileName)

        val content = NSString.stringWithContentsOfFile(
            path,
            encoding = NSUTF8StringEncoding,
            error = null
        )

        return content as String?
    }

    @OptIn(ExperimentalForeignApi::class)
    actual fun clear(fileName: String) {
        val path = getPath(fileName)

        NSFileManager.defaultManager.removeItemAtPath(
            path,
            error = null
        )
    }
}