package com.huixing.financial.base.repo

import com.huixing.financial.model.FundDetail
import com.huixing.financial.model.Rank
import com.huixing.financial.network.FinancialService
import com.huixing.financial.persistence.FundDao
import com.huixing.financial.utils.log
import com.huixing.financial.utils.moshi
import com.skydoves.sandwich.suspendOnSuccess
import javax.inject.Inject

/**
 * used for automatic run analyse fund data
 */
class AnalyseRepo @Inject constructor(
        val financialService: FinancialService,
        val fundDao: FundDao,
) {

    suspend fun analyse(ranks: List<Rank>) {
        val adapter = moshi.adapter(FundDetail::class.java)
        val listFundDetailList = mutableListOf<FundDetail>()
        ranks.forEach {
            financialService.getFundDetail(it.code)
                    .suspendOnSuccess {
                if (data?.code == 200) {
                    listFundDetailList.add(data as FundDetail)
                    log(message = adapter.toJson(data))
                }
            }
        }
    }

}