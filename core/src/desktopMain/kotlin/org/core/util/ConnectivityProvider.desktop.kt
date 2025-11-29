package org.core.util

import dev.jordond.connectivity.Connectivity

actual val connectivityProvider: Connectivity
    get() = Connectivity {
        autoStart = true
        urls("baidu.com")
        port = 80
        pollingIntervalMs = 5.minutes
        timeoutMs = 1.minutes
    }