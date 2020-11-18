package com.huixing.financial.network

import com.huixing.financial.model.FullFund
import com.huixing.financial.model.HotFund
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface FinancialService {

    @GET("v1/fund/hot")
    suspend fun getHotData(): ApiResponse<HotFund>

    @GET("v1/fund/all")
    suspend fun getAllBaseData(): ApiResponse<FullFund>

}