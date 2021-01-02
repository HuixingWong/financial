package com.huixing.financial.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.detectState(hook: (Int) -> Unit){
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            hook.invoke(dy)
        }
    })
}

fun RecyclerView.resumePosition(offset: Int) {
    (layoutManager as? LinearLayoutManager)?.apply {
        when(orientation){
            RecyclerView.HORIZONTAL -> {
                scrollTo(offset, 0)
            }
            RecyclerView.VERTICAL -> {
                scrollTo(0, offset)
            }
        }
    }
}