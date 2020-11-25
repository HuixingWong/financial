package com.huixing.financial.ui.main

import androidx.fragment.app.viewModels
import com.huixing.financial.R
import com.huixing.financial.base.DataBindingFragment
import com.huixing.financial.databinding.FragmentMainBinding
import com.huixing.financial.ui.adapter.HotFundAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment: DataBindingFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()

    override fun bind() {
        binding?.apply {
            lifecycleOwner = this@MainFragment
            adapter = HotFundAdapter()
            vm = viewModel
        }
    }

}