package com.huixing.financial.model

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseDetail(
    val code: Int?,
    val `data`: List<Data>?,
    val message: String?,
    val meta: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.createTypedArrayList(Data),
            parcel.readString(),
            parcel.readString()) {
    }

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
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readValue(Double::class.java.classLoader) as? Double,
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readValue(Double::class.java.classLoader) as? Double,
                parcel.readString()) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(code)
            parcel.writeString(dayGrowth)
            parcel.writeString(expectGrowth)
            parcel.writeValue(expectWorth)
            parcel.writeString(expectWorthDate)
            parcel.writeString(lastMonthGrowth)
            parcel.writeString(lastSixMonthsGrowth)
            parcel.writeString(lastThreeMonthsGrowth)
            parcel.writeString(lastWeekGrowth)
            parcel.writeString(lastYearGrowth)
            parcel.writeString(name)
            parcel.writeValue(netWorth)
            parcel.writeString(netWorthDate)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Data> {
            override fun createFromParcel(parcel: Parcel): Data {
                return Data(parcel)
            }

            override fun newArray(size: Int): Array<Data?> {
                return arrayOfNulls(size)
            }
        }

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(code)
        parcel.writeTypedList(data)
        parcel.writeString(message)
        parcel.writeString(meta)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BaseDetail> {
        override fun createFromParcel(parcel: Parcel): BaseDetail {
            return BaseDetail(parcel)
        }

        override fun newArray(size: Int): Array<BaseDetail?> {
            return arrayOfNulls(size)
        }
    }
}