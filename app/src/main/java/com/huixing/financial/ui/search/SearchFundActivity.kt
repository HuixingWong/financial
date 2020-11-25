package com.huixing.financial.ui.search

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.activity.viewModels
import com.huixing.financial.R
import com.huixing.financial.base.DataBindingActivity
import com.huixing.financial.databinding.ActivitySearchFundBinding
import com.huixing.financial.ui.adapter.SearchFundAdapter
import com.huixing.financial.utils.coroutine.getQueryTextChangeStateFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFundActivity : DataBindingActivity() {

    val binding by binding<ActivitySearchFundBinding>(R.layout.activity_search_fund)
    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = resources.getString(R.string.fund_search)
        binding.apply {
            lifecycleOwner = this@SearchFundActivity
            vm = searchViewModel
            adapter = SearchFundAdapter()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            onActionViewExpanded()
            searchViewModel.search(getQueryTextChangeStateFlow())
        }
        return true
    }
}