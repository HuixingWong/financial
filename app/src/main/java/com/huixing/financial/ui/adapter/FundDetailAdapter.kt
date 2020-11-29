package com.huixing.financial.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.huixing.financial.R
import com.huixing.financial.databinding.DetailItemLayoutBinding
import com.huixing.financial.model.FundDetail
import com.huixing.financial.ui.detail.FundDetailActivity

class FundDetailAdapter : RecyclerView.Adapter<FundDetailAdapter.DetailFundViewHolder>() {

    private val items: MutableList<FundDetail> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailFundViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<DetailItemLayoutBinding>(
                inflater,
                R.layout.detail_item_layout, parent, false
            )
        return DetailFundViewHolder(binding).apply {
            binding.root.setOnClickListener {
                items[adapterPosition].data?.code?.let { it1 ->
                    FundDetailActivity.startActivity(it, it1)
                }
            }
        }
    }


    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: DetailFundViewHolder, position: Int) {
        holder.binding.apply {
            fund = items[position]
            executePendingBindings()
        }
    }

    fun addDetailList(detailList: List<FundDetail>) {
        items.clear()
        items.addAll(detailList)
        notifyDataSetChanged()
    }

    class DetailFundViewHolder(val binding: DetailItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}