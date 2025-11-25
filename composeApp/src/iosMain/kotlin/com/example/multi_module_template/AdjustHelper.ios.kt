package com.example.multi_module_template

import kotlinx.cinterop.ExperimentalForeignApi

//import Adjust.*
//import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
actual val getAdjustHelper: AdjustHelper? =
    object : AdjustHelper{
    override fun init(callback: (deviceId: String, network: String) -> Unit) {
        val yourAppToken = "bgxygfj4r18g"

//        val environment = ADJEnvironmentSandbox
//
//        val adjustConfig = ADJConfig(yourAppToken, environment)
//        adjustConfig.delegate = AdjustDelegateImpl{
//            println("cc_chen ${it?.trackerToken?:""} ${it?.network?:""}")
//            callback(it?.trackerToken?:"", it?.network?:"")
//        }
//        Adjust.initSdk(adjustConfig)
    }
}
//
//@OptIn(ExperimentalForeignApi::class)
//class AdjustDelegateImpl(
//    private val callback: (ADJAttribution?) -> Unit
//) : NSObject(), AdjustDelegateProtocol {
//
//    override fun adjustAttributionChanged(attribution: ADJAttribution?) {
//        callback(attribution)
//    }
//}