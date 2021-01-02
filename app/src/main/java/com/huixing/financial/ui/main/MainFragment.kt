package com.huixing.financial.ui.main

import android.widget.Toast
import androidx.fragment.app.viewModels
import com.huixing.financial.R
import com.huixing.financial.base.DataBindingFragment
import com.huixing.financial.databinding.FragmentMainBinding
import com.huixing.financial.ui.adapter.HotFundAdapter
import com.huixing.financial.utils.detectState
import com.huixing.financial.utils.resumePosition
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment: DataBindingFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()

    override fun bind() {
        binding?.apply {
            lifecycleOwner = this@MainFragment
            adapter = HotFundAdapter().apply {
                stateRestorationPolicy
            }
            vm = viewModel
            mainRecyclerView.resumePosition(viewModel.scrollPosition)
            mainRecyclerView.detectState {  position ->
                viewModel.saveState(position)
            }
        }
        viewModel.toastData.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

}