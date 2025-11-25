package com.example.multi_module_template

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform