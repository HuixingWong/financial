package com.huixing.financial.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.huixing.financial.R
import com.huixing.financial.databinding.HotfundItemLayoutBinding
import com.huixing.financial.model.Rank

class HotFundAdapter : RecyclerView.Adapter<HotFundAdapter.HotFundViewHolder>() {

    private val items: MutableList<Rank> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotFundViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<HotfundItemLayoutBinding>(
                inflater,
                R.layout.hotfund_item_layout, parent, false
            )
        return HotFundViewHolder(binding).apply {

        }
    }


    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: HotFundViewHolder, position: Int) {
        holder.binding.apply {
            fund = items[position]
            executePendingBindings()
        }
    }

    fun addRankList(rankList: List<Rank>) {
        val previous = items.size
        items.addAll(rankList)
        notifyItemRangeChanged(previous, rankList.size)
    }

    class HotFundViewHolder(val binding: HotfundItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}