package com.huixing.financial.repo

import com.huixing.financial.R
import com.huixing.financial.base.App
import com.huixing.financial.base.repo.ShareRepo
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

class SimpleCollectionRepo @Inject constructor(
    val financialService: FinancialService,
    val shareRepo: ShareRepo
) {
    suspend fun fetchAllCollectionData(onError: (String) -> Unit) = flow {
        val codes = shareRepo.collectionsFund.joinToString(separator = ",")
        financialService.getBaseDetail(codes).suspendOnSuccess {
           data.whatIfNotNull {
               if (data?.code == 200) {
                   emit(data)
               }
           }
        }.onError {
            onError(message())
        }.onException {
            onError(App.context.getString(R.string.connection_error))
        }
    }.flowOn(Dispatchers.IO)
}