package com.farad.entertainment.aramkada.utils

import android.widget.ImageView
import coil.load
import coil.request.CachePolicy

fun ImageView.loadImage(any: Any) {
    this.load(any) {
        crossfade(true)
        crossfade(500)
        diskCachePolicy(CachePolicy.ENABLED)
    }
}