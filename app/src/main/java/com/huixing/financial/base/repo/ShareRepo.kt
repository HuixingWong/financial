package com.huixing.financial.base.repo

import com.huixing.financial.model.BaseFundData
import com.huixing.financial.network.FinancialService
import com.huixing.financial.persistence.FundDao
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShareRepo @Inject constructor(
        private val financialService: FinancialService,
        private val fundDao: FundDao) {

    var allFundList: List<BaseFundData>? = null

    suspend fun getAllDataAndSave(onSuccess: () -> Unit, onError: (String) -> Unit) = flow<Boolean>{
        financialService.getAllBaseData().suspendOnSuccess {
            allFundList = fundDao.getAllFundList()
            allFundList.whatIfNotNull {
                onSuccess()
                return@suspendOnSuccess
            }
            data?.data.whatIfNotNull {
                val allFundList = mutableListOf<BaseFundData>()
                it.forEach { baseFund ->
                    allFundList.add(BaseFundData(code = baseFund[0], name = baseFund[2]))
                }
                fundDao.insertFundList(allFundList)
                onSuccess()
                emit(true)
            }
        }.onError {
            onError(message())
        }.onException {
            onError(message())
        }
    }.flowOn(Dispatchers.IO)

}