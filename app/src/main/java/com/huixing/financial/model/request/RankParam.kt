package com.huixing.financial.model.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RankParam(
    var fundType: List<String>? = null,
    var sort: String? = null,
    var fundCompany: String? = null,
    var creatTimeLimit:Long? = null,
    var fundScale: Int? = null,
    var asc: Int? = null,
    val pageIndex: Int? = null,
    val pageSize: Int?= 20
)