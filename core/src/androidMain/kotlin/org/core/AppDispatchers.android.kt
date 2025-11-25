package org.core

import kotlinx.coroutines.Dispatchers

actual val Dispatchers.IO: kotlinx.coroutines.CoroutineDispatcher
    get() = Dispatchers.IO