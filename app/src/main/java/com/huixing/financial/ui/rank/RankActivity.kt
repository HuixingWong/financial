package com.huixing.financial.ui.rank

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.huixing.financial.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RankActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "rank"
        setContentView(R.layout.activity_rank)
    }
}