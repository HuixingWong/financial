package com.huixing.financial.model.request

data class RankParam(
        val fundType: Array<String> = arrayOf("gp", "hh", "zq", "zs", "qdii", "fof"),
        val sort: String = "r",
        val fundCompany: String? = null,
        val creatTimeLimit:Number? = null,
        val fundScale: Number? = null,
        val asc: Number? = null,
        val pageIndex: Number? = null,
        val pageSize: Number?= null
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