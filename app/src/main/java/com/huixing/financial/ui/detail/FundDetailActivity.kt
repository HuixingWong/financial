package com.huixing.financial.ui.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.huixing.financial.R
import com.huixing.financial.base.DataBindingActivity
import com.huixing.financial.databinding.ActivityFundDetailBinding
import com.huixing.financial.utils.argument

class FundDetailActivity : DataBindingActivity() {

    private val fundCode: String by argument(FUND_CODE)
    private val fundDetailViewModel: FundDetailViewModel by viewModels()
    private val binding by binding<ActivityFundDetailBinding>(R.layout.activity_fund_detail)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {

        }
    }

    companion object {
        @VisibleForTesting
        const val FUND_CODE = "FUND_CODE"
        fun startActivity(view: View, fundCode: String) {
            (view.context as? Activity)?.startActivity(Intent(view.context,
                    FundDetailActivity::class.java).apply {
                putExtra(FUND_CODE, fundCode)
            })
        }
    }
}