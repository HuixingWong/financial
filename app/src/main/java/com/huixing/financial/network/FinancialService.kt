package com.huixing.financial.network

import com.huixing.financial.model.FullFund
import com.huixing.financial.model.FundDetail
import com.huixing.financial.model.HotFund
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FinancialService {

    @GET("v1/fund/hot")
    suspend fun getHotData(): ApiResponse<HotFund>

    @GET("v1/fund/all")
    suspend fun getAllBaseData(): ApiResponse<FullFund>

    @GET("detail")
    suspend fun getFundDetail(
        @Query("code") code: String,
        @Query("startDate") startDate: String? = null,
        @Query("endDate") endDate: String? = null,
    ): ApiResponse<FundDetail>

}