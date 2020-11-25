package com.huixing.financial.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class DataBindingDialogFragment<T : ViewDataBinding>(
    @LayoutRes open val layout: Int
) : BottomSheetDialogFragment() {
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
