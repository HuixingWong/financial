package com.huixing.financial.ui.collection

import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.huixing.financial.R
import com.huixing.financial.base.DataBindingFragment
import com.huixing.financial.databinding.FragmentCollectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CollectionFragment : DataBindingFragment<FragmentCollectionBinding>(R.layout.fragment_collection) {

    val collectionViewModel: CollectionViewModel by viewModels()

    override fun bind() { 

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            view?.let { Navigation.findNavController(it).navigateUp() }
        }

    }



}