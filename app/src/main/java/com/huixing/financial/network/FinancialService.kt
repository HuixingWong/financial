package com.huixing.financial.network

import com.huixing.financial.model.FullFund
import com.huixing.financial.model.FundDetail
import com.huixing.financial.model.HotFund
import com.huixing.financial.model.request.RankParam
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.*

interface FinancialService {

    @GET("v1/fund/hot")
    suspend fun getHotData(): ApiResponse<HotFund>

    @GET("v1/fund/all")
    suspend fun getAllBaseData(): ApiResponse<FullFund>

    @GET("v1/fund/detail?")
    suspend fun getFundDetail(
        @Query("code") code: String,
        @Query("startDate") startDate: String? = null,
        @Query("endDate") endDate: String? = null,
    ): ApiResponse<FundDetail>

    // data is same as the hot data
    @Headers("Content-Type: application/json")
    @POST("v1/fund/rank")
    suspend fun fetchRankData(@Body rankParam: String): ApiResponse<HotFund>

}