package com.huixing.financial.utils

import com.huixing.financial.model.request.RankParam
import com.squareup.moshi.Moshi

val moshi = Moshi.Builder().build()

val rankParamAdapter = moshi.adapter(RankParam::class.java)