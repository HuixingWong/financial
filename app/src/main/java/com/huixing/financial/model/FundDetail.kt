package com.huixing.financial.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FundDetail(
    val code: Int?,
    val `data`: FundDetailData?,
    val message: String?,
    val meta: String?,
    var analysisResult: String? = null
)

@JsonClass(generateAdapter = true)
data class FundDetailData(
    val buyMin: String?,
    val buyRate: String?,
    val buySourceRate: String?,
    val code: String?,
    val dayGrowth: String?,
    val expectGrowth: String?,
    val expectWorth: Double?,
    val expectWorthDate: String?,
    val fundScale: String?,
    val lastMonthGrowth: String?,
    val lastSixMonthsGrowth: String?,
    val lastThreeMonthsGrowth: String?,
    val lastWeekGrowth: String?,
    val lastYearGrowth: String?,
    val manager: String?,
    val name: String?,
    val netWorth: Double?,
    val netWorthData: List<List<String>>?,
    val netWorthDate: String?,
    val totalNetWorthData: List<List<String>>?,
    val totalWorth: Double?,
    val type: String?
)

