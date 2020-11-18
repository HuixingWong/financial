package com.huixing.financial.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.huixing.financial.R
import com.huixing.financial.databinding.BasefundItemLayoutBinding
import com.huixing.financial.model.BaseFundData

class SearchFundAdapter : RecyclerView.Adapter<SearchFundAdapter.SearchFundViewHolder>() {

    private val items: MutableList<BaseFundData> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchFundViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<BasefundItemLayoutBinding>(
                inflater,
                R.layout.basefund_item_layout, parent, false
            )
        return SearchFundViewHolder(binding).apply {

        }
    }


    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SearchFundViewHolder, position: Int) {
        holder.binding.apply {
            fund = items[position]
            executePendingBindings()
        }
    }

    fun addList(list: List<BaseFundData>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    class SearchFundViewHolder(val binding: BasefundItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}