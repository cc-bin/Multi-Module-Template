package org.core.util

import dev.jordond.connectivity.Connectivity
import io.ktor.server.util.url

actual val connectivityProvider: Connectivity
    get() = Connectivity {
        autoStart(true)
    }