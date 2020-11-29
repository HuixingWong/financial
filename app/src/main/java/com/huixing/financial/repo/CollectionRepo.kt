package com.huixing.financial.repo

import com.huixing.financial.base.analyse.AnalyseUtil
import com.huixing.financial.base.repo.ShareRepo
import com.huixing.financial.model.FundDetail
import com.huixing.financial.network.FinancialService
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CollectionRepo @Inject constructor(
    val financialService: FinancialService,
    val shareRepo: ShareRepo
) {
    suspend fun fetchAllCollectionData() = flow {
        val fundDetailList = mutableListOf<FundDetail>()
        shareRepo.collectionsFund.forEach {
            financialService.getFundDetail(it).suspendOnSuccess {
                data.whatIfNotNull { fundDetail ->
                    fundDetailList.add(AnalyseUtil.analyseSingleFundDetail(fundDetail))
                    emit(fundDetailList)
                }
            }
        }
    }.flowOn(Dispatchers.IO)
}