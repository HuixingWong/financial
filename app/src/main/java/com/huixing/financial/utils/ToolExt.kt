package com.huixing.financial.utils

import com.huixing.financial.model.request.RankParam
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

val moshi: Moshi = Moshi.Builder().build()

val rankParamAdapter: JsonAdapter<RankParam> = moshi.adapter(RankParam::class.java)