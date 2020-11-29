package com.huixing.financial.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.huixing.financial.model.BaseFundData
import com.huixing.financial.model.FundDetail
import com.huixing.financial.model.Rank
import com.huixing.financial.ui.adapter.FundDetailAdapter
import com.huixing.financial.ui.adapter.HotFundAdapter
import com.huixing.financial.ui.adapter.SearchFundAdapter
import com.skydoves.whatif.whatIfNotNullOrEmpty

object RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("adapterHotFundList")
    fun bindHotFundList(view: RecyclerView, rankList: List<Rank>?) {
        rankList.whatIfNotNullOrEmpty {
            (view.adapter as? HotFundAdapter)?.addRankList(it)
        }
    }

    @JvmStatic
    @BindingAdapter("adapterCollectionFundList")
    fun bindCollectFundList(view: RecyclerView, rankList: List<FundDetail>?) {
        rankList.whatIfNotNullOrEmpty {
            (view.adapter as? FundDetailAdapter)?.addDetailList(it)
        }
    }


    @JvmStatic
    @BindingAdapter("adapterSearchFundList")
    fun bindSearchFundList(view: RecyclerView, rankList: List<BaseFundData>?) {
        rankList.whatIfNotNullOrEmpty {
            (view.adapter as? SearchFundAdapter)?.addList(it)
        }
    }
}