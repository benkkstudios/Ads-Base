package com.benkkstudios.ads.base.base

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.Composable
import com.benkkstudios.ads.base.BannerSize
import com.benkkstudios.ads.base.Provider
import com.benkkstudios.ads.base.ProviderUnit
import com.benkkstudios.ads.base.enums.NativeSize
import java.util.concurrent.atomic.AtomicBoolean

abstract class BaseFactory(var unit: ProviderUnit) {
    private val isInitialized = AtomicBoolean(false)
    val provider: Provider by lazy { setCompany() }
    abstract fun setCompany(): Provider
    open fun init(activity: Activity, onInitializationComplete: (() -> Unit)? = null) {
        if (isInitialized.getAndSet(true)) {
            onInitializationComplete?.invoke()
            return
        }
    }

    open fun loadInterstitial(activity: Activity) {
        if (unit.interstitial.isBlank()) return
    }

    abstract fun showInterstitial(activity: Activity, onSuccess: (() -> Unit)?, onError: ((String) -> Unit)?)
    open fun loadReward(activity: Activity) {
        if (unit.reward.isBlank()) return
    }

    abstract fun showReward(activity: Activity, onSuccess: (() -> Unit)?, onError: ((String) -> Unit)?)
    open fun loadOpenAd(context: Context, onComplete: (() -> Unit)? = null) {
        if (unit.open.isBlank()) {
            onComplete?.invoke()
            return
        }
    }

    abstract fun showOpenAd(activity: Activity, onFailedToShow: (() -> Unit)? = null)

    @Composable
    open fun BannerCompose(size: BannerSize, onAdFailedToLoad: () -> Unit) {
        if (unit.banner.isBlank()) {
            onAdFailedToLoad.invoke()
            return
        }
    }

    @Composable
    open fun NativeCompose(size: NativeSize, onAdFailedToLoad: () -> Unit) {
        if (unit.native.isBlank()) {
            onAdFailedToLoad.invoke()
            return
        }
    }

}