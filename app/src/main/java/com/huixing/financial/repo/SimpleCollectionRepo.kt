package com.huixing.financial.repo

import com.huixing.financial.base.repo.ShareRepo
import com.huixing.financial.network.FinancialService
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SimpleCollectionRepo @Inject constructor(
    val financialService: FinancialService,
    val shareRepo: ShareRepo
) {
    suspend fun fetchAllCollectionData() = flow {
        val codes = shareRepo.collectionsFund.joinToString(separator = ",")
        financialService.getBaseDetail(codes).suspendOnSuccess {
           data.whatIfNotNull {
               if (data?.code == 200) {
                   emit(data)
               }
           }
        }
    }.flowOn(Dispatchers.IO)
}