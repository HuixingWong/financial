package com.huixing.financial.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.detectState(hook: (Int) -> Unit){
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager
            layoutManager?.let {
                hook.invoke(when (layoutManager) {
                    is LinearLayoutManager -> layoutManager.findLastVisibleItemPosition()
                    is GridLayoutManager -> layoutManager.findLastVisibleItemPosition()
                    else -> return
                })
            }
        }
    })
}