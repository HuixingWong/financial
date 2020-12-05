package com.huixing.financial.repo

import com.huixing.financial.R
import com.huixing.financial.base.App
import com.huixing.financial.base.analyse.AnalyseUtil
import com.huixing.financial.base.repo.ShareRepo
import com.huixing.financial.model.FundDetail
import com.huixing.financial.network.FinancialService
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
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
    suspend fun fetchAllCollectionData(onError: (String) -> Unit) = flow {
        val fundDetailList = mutableListOf<FundDetail>()
        shareRepo.collectionsFund.forEach {
            financialService.getFundDetail(it).suspendOnSuccess {
                data.whatIfNotNull { fundDetail ->
                    fundDetailList.add(AnalyseUtil.analyseSingleFundDetail(fundDetail))
                    emit(fundDetailList)
                }
            }.onError {
                onError(message())
            }.onException {
                onError(App.context.getString(R.string.connection_error))
            }
        }
    }.flowOn(Dispatchers.IO)
}