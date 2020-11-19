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
                    this.data?.data?.netWorthData?.forEach {
                        fundMap[it[0]] = it[1]
                    }
                    emit(this.data?.data)
                }.onError {
                    onError(this.statusCode.code.toString())
                }.onException {
                    onError(this.message.toString())
                }
    }.flowOn(Dispatchers.IO)


    suspend fun calculate(netWorthData: List<List<String>>,
                          startDate: String?,
                          endDate: String?,
                          onError: (String) -> Unit) =
            flow {
                var startValue = -1.0
                var endValue = -1.0
                netWorthData.forEach { worthData ->
                    if (worthData[0] == startDate) {
                        startValue = worthData[1].toDouble()
                    } else if (worthData[0] == endDate) {
                        endValue = worthData[1].toDouble()
                        return@forEach
                    }
                }
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