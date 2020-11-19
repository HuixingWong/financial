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

    private val fundMap = mutableMapOf<String, String>()

    suspend fun fetchFundDetail(
            code: String,
            startDate: String? = null,
            endDate: String? = null,
            onError: (String) -> Unit
    ) = flow {
        financialService.getFundDetail(code, startDate, endDate)
                .suspendOnSuccess {
                    emit(this.data?.data)
                    this.data?.data?.netWorthData?.forEach {
                        fundMap[it[0]] = it[1]
                    }
                }.onError {
                    onError(this.statusCode.code.toString())
                }.onException {
                    onError(this.message.toString())
                }
    }.flowOn(Dispatchers.IO)


    suspend fun calculate(startDate: String?,
                          endDate: String?,
                          onError: (String) -> Unit) =
            flow {
                val startValue = fundMap[startDate]?.toDouble()?: -1.0
                val endValue = fundMap[endDate]?.toDouble()?: -1.0
                if (startValue < 0) {
                    onError("开始日期有误")
                    return@flow
                }
                if (endValue < 0) {
                    onError("结束日期日期有误")
                    return@flow
                }
                emit(endValue / startValue)
            }.flowOn(Dispatchers.IO)

}