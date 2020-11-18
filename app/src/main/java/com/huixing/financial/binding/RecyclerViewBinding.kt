package com.huixing.financial.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.huixing.financial.model.Rank
import com.huixing.financial.ui.adapter.HotFundAdapter
import com.skydoves.whatif.whatIfNotNullOrEmpty

object RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("adapterHotFundList")
    fun bindAdapterPokemonList(view: RecyclerView, rankList: List<Rank>?) {
        rankList.whatIfNotNullOrEmpty {
            (view.adapter as? HotFundAdapter)?.addRankList(it)
        }
    }
}