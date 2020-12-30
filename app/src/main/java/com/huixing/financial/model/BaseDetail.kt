package com.huixing.financial.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
@JsonClass(generateAdapter = true)
data class BaseDetail(
        val code: Int?,
        val `data`: @RawValue List<Data>?,
        val message: String?,
        val meta: String?
) : Parcelable {
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
    ) : Parcelable
}