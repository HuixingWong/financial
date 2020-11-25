package com.huixing.financial.model.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RankParam(
        val fundType: Array<String> = arrayOf("gp", "hh", "zq", "zs", "qdii", "fof"),
        val sort: String = "r",
        val fundCompany: String? = null,
        val creatTimeLimit:Long? = null,
        val fundScale: Int? = null,
        val asc: Int? = null,
        val pageIndex: Int? = null,
        val pageSize: Int?= 20
) {



    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as RankParam
        if (!fundType.contentEquals(other.fundType)) return false
        return true
    }
    override fun hashCode(): Int {
        return fundType.contentHashCode()
    }
}