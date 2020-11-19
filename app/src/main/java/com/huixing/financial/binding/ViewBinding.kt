package com.huixing.financial.binding

import android.app.DatePickerDialog
import android.os.Build
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import com.huixing.financial.utils.TimeUtil
import com.skydoves.whatif.whatIfNotNullOrEmpty

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

    @RequiresApi(Build.VERSION_CODES.N)
    @JvmStatic
    @BindingAdapter("pickDate")
    fun bindPickDate(view: View, backToView: Boolean) {
        view.setOnClickListener {
            DatePickerDialog(view.context).apply {
                setOnDateSetListener { _, year, month, dayOfMonth ->
                    if (backToView) {
                        (view as? TextView)?.text = TimeUtil.getDateStr(year,month,dayOfMonth)
                    }
                }
            }.show()
        }
    }

}