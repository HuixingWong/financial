package com.huixing.financial.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HotFund(
    val code: Int?,
    val `data`: Data?,
    val message: String?,
    val meta: Any?
)

@JsonClass(generateAdapter = true)
data class Data(
    val allPages: Int?,
    val pageIndex: Int?,
    val pageSize: Int?,
    val rank: List<Rank>?
)

@JsonClass(generateAdapter = true)
data class Rank(
    val code: String,
    val dayGrowth: String?,
    val expectGrowth: String?,
    val expectWorth: Double?,
    val expectWorthDate: String?,
    val fundType: String?,
    val lastMonthGrowth: String?,
    val lastSixMonthsGrowth: String?,
    val lastThreeMonthsGrowth: String?,
    val lastWeekGrowth: String?,
    val lastYearGrowth: String?,
    val name: String?,
    val netWorth: Double?,
    val netWorthDate: String?,
    val thisYearGrowth: String?
)