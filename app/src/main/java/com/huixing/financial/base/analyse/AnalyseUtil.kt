package com.huixing.financial.base.analyse

import androidx.annotation.WorkerThread
import com.huixing.financial.model.FundDetail

object AnalyseUtil {

    @WorkerThread
    fun analyseSingleFundDetail(
            fundDetail: FundDetail,
    ): FundDetail {
        val analysisResult = StringBuilder()
        var declineDays = 0
        kotlin.runCatching {
            fundDetail.data?.netWorthData?.run {
                asReversed().forEach {
                    if (it[2].toDouble() < 0) {
                        declineDays++
                    } else {
                        return@run
                    }
                }
            }
        }
        analysisResult.append("连续下跌${declineDays}天")
        fundDetail.analysisResult = analysisResult.toString()
        return fundDetail
    }

}