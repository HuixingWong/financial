package com.huixing.financial.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
data class FullFund(
        val code: Int?,
        val `data`: List<List<String>>?,
        val message: String?,
        val meta: Any?
)

@Entity
@Parcelize
data class BaseFundData(
        @PrimaryKey
        @ColumnInfo(name = "code") val code: String,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "collect") val isCollect: Boolean = false
) : Parcelable