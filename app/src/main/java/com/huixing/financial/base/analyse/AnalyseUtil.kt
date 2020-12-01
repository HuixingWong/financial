package com.huixing.financial.base.analyse

import androidx.annotation.WorkerThread
import com.huixing.financial.model.FundDetail
import kotlin.math.max
import kotlin.math.min

object AnalyseUtil {

    @WorkerThread
    fun analyseSingleFundDetail(
            fundDetail: FundDetail,
    ): FundDetail {
        val analysisResult = StringBuilder()
        var declineDays = 0
        var maxProfit = 0.0
        kotlin.runCatching {
            val netWorthData = fundDetail.data?.netWorthData
            val reverseData = netWorthData?.asReversed()
            reverseData?.run {
                forEach {
                    if (it[2].toDouble() < 0) {
                        declineDays++
                    } else {
                        return@run
                    }
                }
            }

            netWorthData?.let {
                maxProfit = analyseMaxProfit(it)
            }
        }

        analysisResult.append("连续下跌${declineDays}天\n")
        analysisResult.append("\n最佳单次交易获利可达到${(maxProfit * 100).toInt()}%")
        fundDetail.analysisResult = analysisResult.toString()
        return fundDetail
    }

    @WorkerThread
    fun analyseMaxProfit(worthData: List<List<String>>): Double {
        var cost = Double.MAX_VALUE
        var profit = 0.0
        worthData.forEach {
            cost = min(cost, it[1].toDouble())
            profit = max(profit, it[1].toDouble() - cost)
        }
        return profit / cost
    }

}