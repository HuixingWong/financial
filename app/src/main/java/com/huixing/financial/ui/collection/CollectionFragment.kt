package com.huixing.financial.ui.collection

import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.viewModels
import com.huixing.financial.R
import com.huixing.financial.base.DataBindingFragment
import com.huixing.financial.databinding.FragmentCollectionBinding
import com.huixing.financial.ui.adapter.FundDetailAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CollectionFragment : DataBindingFragment<FragmentCollectionBinding>(R.layout.fragment_collection) {

    private val collectionViewModel: CollectionViewModel by viewModels()

    override fun bind() {
        binding?.apply {
            lifecycleOwner = this@CollectionFragment
            vm = collectionViewModel
            adapter = FundDetailAdapter()
            setHasOptionsMenu(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.rank_menu, menu)
    }

}