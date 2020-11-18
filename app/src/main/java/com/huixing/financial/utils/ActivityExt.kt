package com.huixing.financial.utils

import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import com.huixing.financial.base.App

@MainThread
inline fun <reified VM : ViewModel> ComponentActivity.appViewModels(
        noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
): Lazy<VM> {
    val factoryPromise = factoryProducer ?: {
        defaultViewModelProviderFactory
    }
    return ViewModelLazy(VM::class, { (application as App).viewModelStore }, factoryPromise)
}

/** initialize a parcelable argument lazily. */
fun <T : Parcelable> ComponentActivity.argument(key: String): Lazy<T> {
    return lazy { requireNotNull(intent.getParcelableExtra<T>(key)) }
}
