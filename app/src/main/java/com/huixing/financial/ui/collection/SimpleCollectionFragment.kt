package com.huixing.financial.ui.collection

import androidx.fragment.app.viewModels
import com.huixing.financial.R
import com.huixing.financial.base.DataBindingFragment
import com.huixing.financial.databinding.FragmentSimpleCollectionBinding
import com.huixing.financial.ui.adapter.BaseFundAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SimpleCollectionFragment :
    DataBindingFragment<FragmentSimpleCollectionBinding>(R.layout.fragment_simple_collection) {

    private val simpleCollectionViewModel: SimpleCollectionViewModel by viewModels()

    override fun bind() {
        binding?.apply {
            lifecycleOwner = this@SimpleCollectionFragment
            vm = simpleCollectionViewModel
            adapter = BaseFundAdapter()
        }
    }

}