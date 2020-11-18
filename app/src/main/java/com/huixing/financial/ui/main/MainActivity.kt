package com.huixing.financial.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.huixing.financial.R
import com.huixing.financial.base.DataBindingActivity
import com.huixing.financial.databinding.ActivityMainBinding
import com.huixing.financial.ui.adapter.HotFundAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : DataBindingActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val binding: ActivityMainBinding by binding(R.layout.activity_main)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@MainActivity
            adapter = HotFundAdapter()
            vm = viewModel
        }
        viewModel.fetchHotData()
    }

}