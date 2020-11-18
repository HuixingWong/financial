package com.huixing.financial.repo

import com.huixing.financial.network.FinancialService
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FundDetailRepo @Inject constructor(
    private val financialService: FinancialService
) {

    suspend fun fetchFundDetail(
        code: String,
        startDate: String? = null,
        endDate: String? = null,
        onError: (String) -> Unit
    ) = flow {
        financialService.getFundDetail(code, startDate, endDate)
            .suspendOnSuccess {
                emit(this.data?.data)
            }.onError {
                onError(this.statusCode.code.toString())
            }.onException {
                onError(this.message.toString())
            }
    }.flowOn(Dispatchers.IO)

}