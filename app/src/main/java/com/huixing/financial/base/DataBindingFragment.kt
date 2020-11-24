package com.huixing.financial.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class DataBindingFragment<T : ViewDataBinding>(
    @LayoutRes open val layout: Int
) : Fragment() {
    internal var binding: T? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = DataBindingUtil
        .inflate<T>(inflater, layout, container, false)
        .apply {
            binding = this
            binding.apply {
                bind()
            }
        }
        .root
    abstract fun bind()
}
