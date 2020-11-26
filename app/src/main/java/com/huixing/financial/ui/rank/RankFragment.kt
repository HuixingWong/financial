package com.huixing.financial.ui.rank

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.huixing.financial.R
import com.huixing.financial.base.DataBindingFragment
import com.huixing.financial.base.viewmodel.SharedViewModel
import com.huixing.financial.databinding.FragmentRankBinding
import com.huixing.financial.ui.adapter.HotFundAdapter
import com.huixing.financial.utils.appViewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RankFragment : DataBindingFragment<FragmentRankBinding>(R.layout.fragment_rank) {

    private val rankViewModel: RankViewModel by activityViewModels()
    private val sharedViewModel: SharedViewModel by appViewModels()

    override fun bind() {
        binding?.apply {
            setHasOptionsMenu(true)
            lifecycleOwner = this@RankFragment
            adapter = HotFundAdapter()
            vm = rankViewModel
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater)
        menuInflater.inflate(R.menu.rank_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.rankFilter -> {
                view?.let { it1 ->
                    Navigation.findNavController(it1)
                            .navigate(R.id.action_rankFragment_to_rankDialogFragment)
                }
            }
            R.id.analyse -> {
                runAnalyse()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun runAnalyse() {
        rankViewModel.rankData.value?.data?.rank?.let {
            sharedViewModel.analyseRankData(it)
        }
    }

}