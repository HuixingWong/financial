package com.huixing.financial.model

import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
@JsonClass(generateAdapter = true)
data class BaseDetail(
        val code: Int?,
        val `data`: @RawValue List<Data>?,
        val message: String?,
        val meta: String?
) {
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Data(
            val code: String?,
            val dayGrowth: String?,
            val expectGrowth: String?,
            val expectWorth: Double?,
            val expectWorthDate: String?,
            val lastMonthGrowth: String?,
            val lastSixMonthsGrowth: String?,
            val lastThreeMonthsGrowth: String?,
            val lastWeekGrowth: String?,
            val lastYearGrowth: String?,
            val name: String?,
            val netWorth: Double?,
            val netWorthDate: String?
    )
}