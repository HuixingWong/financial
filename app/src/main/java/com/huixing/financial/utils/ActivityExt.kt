package com.huixing.financial.utils

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
