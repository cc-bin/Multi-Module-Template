package org.core

import kotlinx.coroutines.Dispatchers

actual val kotlinx.coroutines.Dispatchers.IO: kotlinx.coroutines.CoroutineDispatcher
    get() = Dispatchers.Default