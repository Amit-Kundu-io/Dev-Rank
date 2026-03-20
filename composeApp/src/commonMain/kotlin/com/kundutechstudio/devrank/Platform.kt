package com.kundutechstudio.devrank

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform