package com.huixing.financial.binding

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.huixing.financial.R
import com.huixing.financial.R.color.f_green
import com.huixing.financial.utils.TimeUtil
import com.huixing.financial.utils.toDate
import com.skydoves.whatif.whatIfNotNullOrEmpty
import java.time.LocalDate

object ViewBinding {

    @JvmStatic
    @BindingAdapter("toast")
    fun bindToast(view: View, text: String?) {
        text.whatIfNotNullOrEmpty {
            Toast.makeText(view.context, it, Toast.LENGTH_SHORT).show()
        }
    }

    @JvmStatic
    @BindingAdapter("gone")
    fun bindGone(view: View, shouldBeGone: Boolean) {
        view.visibility = if (shouldBeGone) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    @SuppressLint("NewApi")
    @JvmStatic
    @BindingAdapter("pickDate")
    fun bindPickDate(view: View, backToView: Boolean) {
        view.setOnClickListener {
            val date = kotlin.runCatching {
                (view as? TextView)?.text?.toString()?.toDate()
            }.getOrNull() ?: LocalDate.now()
            DatePickerDialog(view.context, { _, year, month, dayOfMonth ->
                if (backToView) {
                    (view as? TextView)?.text = TimeUtil.getDateStr(year, month, dayOfMonth)
                }
            }, date.year, date.monthValue - 1, date.dayOfMonth).show()
        }
    }

    @JvmStatic
    @BindingAdapter("bindCodeColor")
    fun bindColor(view: View, color: String?) {
        color.whatIfNotNullOrEmpty {
            view.setBackgroundColor(Color.parseColor("#$color"))
        }
    }

    @JvmStatic
    @BindingAdapter("bindNumberColor")
    fun bindColor(view: TextView, number: String?) {
        number.whatIfNotNullOrEmpty {
            number?.toDouble()?.apply {
                view.text = number
                view.setTextColor(
                    view.resources.getColor(
                        if (this > 0) R.color.f_red else f_green, null
                    )
                )
                return
            }
        }
        view.text = "暂无数据"
    }

    @JvmStatic
    @BindingAdapter("isCollection")
    fun bindClickCollection(view: View, isCollection: Boolean) {
        (view as? ImageView)?.apply {
            setImageResource(
                if (isCollection)
                    R.drawable.star_on else R.drawable.star_off
            )
        }
    }
}