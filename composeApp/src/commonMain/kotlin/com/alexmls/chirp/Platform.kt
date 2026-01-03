package com.alexmls.chirp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform