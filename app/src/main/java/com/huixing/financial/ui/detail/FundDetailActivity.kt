package com.huixing.financial.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.huixing.financial.R
import com.huixing.financial.base.DataBindingActivity
import com.huixing.financial.databinding.ActivityFundDetailBinding
import com.huixing.financial.repo.FundDetailRepo
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FundDetailActivity : DataBindingActivity() {

//    @Inject
//    lateinit var fundViewModelFactory: FundDetailViewModel.AssistedFactory

    @Inject
    lateinit var fundDetailRepo: FundDetailRepo

    private val fundDetailViewModel: FundDetailViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return FundDetailViewModel(fundDetailRepo, intent.getStringExtra(FUND_CODE)?:"") as T
            }
        }

    }
    private val binding by binding<ActivityFundDetailBinding>(R.layout.activity_fund_detail)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = resources.getString(R.string.fund_detail)
        binding.apply {
            lifecycleOwner = this@FundDetailActivity
            vm = fundDetailViewModel
        }
    }

    companion object {
        const val FUND_CODE = "FUND_CODE"
        fun startActivity(view: View, fundCode: String) {
            val intent = Intent(view.context, FundDetailActivity::class.java)
            intent.putExtra(FUND_CODE, fundCode)
            view.context?.startActivity(intent)
        }
    }

}