package com.benkkstudios.ads.base

import java.util.concurrent.atomic.AtomicBoolean

object AdsConstant {
    var DEBUG_MODE: Boolean = false
    var MAX_NATIVE_VARIANT = 3
    val openAdsShowing = AtomicBoolean(false)
}