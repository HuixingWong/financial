package com.huixing.financial.utils

import android.os.Parcelable
import android.view.LayoutInflater
import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
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

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }
