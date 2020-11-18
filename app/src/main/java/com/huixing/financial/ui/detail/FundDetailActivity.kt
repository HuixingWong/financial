package com.huixing.financial.ui.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.huixing.financial.R
import com.huixing.financial.base.DataBindingActivity
import com.huixing.financial.databinding.ActivityFundDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FundDetailActivity : DataBindingActivity() {

    private val fundDetailViewModel: FundDetailViewModel by viewModels()
    private val binding by binding<ActivityFundDetailBinding>(R.layout.activity_fund_detail)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "基金详情"
        intent.getStringExtra(FUND_CODE)?.let { fundDetailViewModel.fetchFundDetail(it) }
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
            (view.context as? Activity)?.startActivity(intent)
        }
    }
}