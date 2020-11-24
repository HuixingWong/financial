package com.huixing.financial.ui.main

import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.viewModels
import com.huixing.financial.R
import com.huixing.financial.base.DataBindingFragment
import com.huixing.financial.base.viewmodel.SharedViewModel
import com.huixing.financial.databinding.FragmentMainBinding
import com.huixing.financial.ui.adapter.HotFundAdapter
import com.huixing.financial.ui.search.SearchFundActivity
import com.huixing.financial.utils.appViewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment: DataBindingFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by appViewModels()


    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater)
        menuInflater.inflate(R.menu.options_menu, menu)
        menu.findItem(R.id.search).setOnMenuItemClickListener {
            Intent(requireActivity(), SearchFundActivity::class.java).apply {
                startActivity(this)
            }
            return@setOnMenuItemClickListener false
        }
        sharedViewModel.showSearch.observe(this, {
            menu.findItem(R.id.search).isVisible = it
        })
    }

    override fun bind() {
        binding?.apply {
            setHasOptionsMenu(true)
            lifecycleOwner = this@MainFragment
            adapter = HotFundAdapter()
            vm = viewModel
            svm = sharedViewModel
        }
    }

}