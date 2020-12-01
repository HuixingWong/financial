package com.huixing.financial.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.huixing.financial.R
import com.huixing.financial.databinding.BasedetailfundItemLayoutBinding
import com.huixing.financial.model.BaseDetail
import com.huixing.financial.ui.detail.FundDetailActivity

class BaseFundAdapter : RecyclerView.Adapter<BaseFundAdapter.BaseFundViewHolder>() {

    private val items: MutableList<BaseDetail.Data> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseFundViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<BasedetailfundItemLayoutBinding>(
                inflater,
                R.layout.basedetailfund_item_layout, parent, false
            )
        return BaseFundViewHolder(binding).apply {
            binding.root.setOnClickListener {
                items[adapterPosition].code?.let { it1 ->
                    FundDetailActivity.startActivity(it, it1)
                }
            }
        }
    }


    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseFundViewHolder, position: Int) {
        holder.binding.apply {
            fund = items[position]
            executePendingBindings()
        }
    }

    fun addBaseList(baseList: List<BaseDetail.Data>) {
        items.clear()
        items.addAll(baseList)
        notifyDataSetChanged()
    }

    class BaseFundViewHolder(val binding: BasedetailfundItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}