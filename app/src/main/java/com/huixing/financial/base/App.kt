package com.huixing.financial.base

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application(), ViewModelStoreOwner {

    private val mAppViewModelStore: ViewModelStore by lazy {
        ViewModelStore()
    }

    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModelStore
    }

}