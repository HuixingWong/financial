package com.huixing.financial.ui.main

import android.os.Bundle
import com.huixing.financial.R
import com.huixing.financial.base.DataBindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : DataBindingActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }

}