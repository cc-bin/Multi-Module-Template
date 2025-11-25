package com.example.multi_module_template


interface AdjustHelper {
    fun init(callback: (deviceId: String, network: String)-> Unit)
}

expect val getAdjustHelper: AdjustHelper?