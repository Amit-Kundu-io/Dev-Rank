package com.kunduthchstudio.utility.Logger

// commonMain
expect object Logger {
    fun d(tag: String, message: String)
    fun e(tag: String, message: String, throwable: Throwable? = null)
}
