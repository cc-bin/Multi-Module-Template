package org.core.ui.ext

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.memory.MemoryCache
import coil3.request.CachePolicy

internal val LocalAppImageLoader = compositionLocalOf<ImageLoader> {
    error("No ImageLoader provided. Have you forgotten the LocalImageLoaderProvider?")
}

fun getDefaultImageLoader(context: PlatformContext): ImageLoader = ImageLoader
    .Builder(context)
    .diskCachePolicy(CachePolicy.ENABLED)
    .networkCachePolicy(CachePolicy.READ_ONLY)
    .memoryCachePolicy(CachePolicy.ENABLED)
    .memoryCache {
        MemoryCache.Builder()
            .maxSizePercent(context, 0.25)
            .build()
    }
    .build()

@Composable
fun LocalImageLoaderProvider(imageLoader: ImageLoader, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalAppImageLoader provides imageLoader) {
        content()
    }
}
