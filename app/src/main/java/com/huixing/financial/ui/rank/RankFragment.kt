package com.huixing.financial.ui.rank

import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.huixing.financial.R
import com.huixing.financial.base.DataBindingFragment
import com.huixing.financial.databinding.FragmentRankBinding
import com.huixing.financial.ui.adapter.HotFundAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RankFragment : DataBindingFragment<FragmentRankBinding>(R.layout.fragment_rank) {

    private val rankViewModel: RankViewModel by activityViewModels()

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
        menu.findItem(R.id.rankFilter).setOnMenuItemClickListener {
            view?.let { it1 ->
                Navigation.findNavController(it1)
                    .navigate(R.id.action_rankFragment_to_rankDialogFragment)
            }
            return@setOnMenuItemClickListener false
        }
    }

}