package com.huixing.financial.ui.rank

import androidx.fragment.app.activityViewModels
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
            lifecycleOwner = this@RankFragment
            adapter = HotFundAdapter()
            vm = rankViewModel
        }
    }

}