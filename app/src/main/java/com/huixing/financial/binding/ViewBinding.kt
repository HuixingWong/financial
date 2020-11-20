package com.huixing.financial.binding

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.BindingAdapter
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

}