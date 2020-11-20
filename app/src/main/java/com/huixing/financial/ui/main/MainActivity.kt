package com.huixing.financial.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import com.huixing.financial.R
import com.huixing.financial.base.DataBindingActivity
import com.huixing.financial.base.viewmodel.SharedViewModel
import com.huixing.financial.databinding.ActivityMainBinding
import com.huixing.financial.ui.adapter.HotFundAdapter
import com.huixing.financial.ui.search.SearchFundActivity
import com.huixing.financial.utils.appViewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : DataBindingActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by appViewModels()
    private val binding: ActivityMainBinding by binding(R.layout.activity_main)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@MainActivity
            adapter = HotFundAdapter()
            vm = viewModel
            svm = sharedViewModel
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        menu.findItem(R.id.search).setOnMenuItemClickListener {
            Intent(this, SearchFundActivity::class.java).apply {
                startActivity(this)
            }
            return@setOnMenuItemClickListener false
        }
        sharedViewModel.showSearch.observe(this, {
            menu.findItem(R.id.search).isVisible = it
        })
        return true
    }

}