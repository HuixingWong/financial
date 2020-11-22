package com.huixing.financial.ui.rank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.huixing.financial.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RankActivity : AppCompatActivity() {

    val rankViewModel: RankViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rank)
    }
}