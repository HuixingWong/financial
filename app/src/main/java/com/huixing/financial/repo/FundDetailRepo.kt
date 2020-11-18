package com.huixing.financial.repo

import com.huixing.financial.network.FinancialService
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIf
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
        endDate: String? = null
    ) = flow {
        financialService.getFundDetail(code, startDate, endDate)
            .suspendOnSuccess {
                data?.whatIf(code.equals(200)) {
                    emit(this.data)
                }
            }
    }.flowOn(Dispatchers.IO)

}