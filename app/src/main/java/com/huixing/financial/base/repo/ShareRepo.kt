package com.huixing.financial.base.repo

import com.huixing.financial.model.BaseFundData
import com.huixing.financial.network.FinancialService
import com.huixing.financial.persistence.FundDao
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import com.skydoves.whatif.whatIfNotNullOrEmpty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShareRepo @Inject constructor(
        private val financialService: FinancialService,
        private val fundDao: FundDao) {

    var mAllFundList: List<BaseFundData>? = null
    var collectionsFund : MutableSet<String> = mutableSetOf()

    suspend fun getAllDataAndSave(
            onSuccess: () -> Unit,
            onError: (String) -> Unit)
            = flow {
        mAllFundList = fundDao.getAllFundList()
        mAllFundList.whatIfNotNullOrEmpty {
            onSuccess()
            return@flow
        }
        financialService.getAllBaseData().suspendOnSuccess {
            data?.data.whatIfNotNull {
                val allFundList = mutableListOf<BaseFundData>()
                it.forEach { baseFund ->
                    allFundList.add(BaseFundData(code = baseFund[0], name = baseFund[2]))
                }
                fundDao.insertFundList(allFundList)
                mAllFundList = allFundList
                onSuccess()
                emit(true)
            }
        }.onError {
            onError(message())
        }.onException {
            onError(message())
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getAllCollectionFund() = flow {
        if (collectionsFund.size == 0) {
            fundDao.getCollectionFund().forEach {
                collectionsFund.add(it.code)
            }
        }
        emit(collectionsFund)
    }.flowOn(Dispatchers.IO)

    private suspend fun saveCollection(code: String) {
        val fund = fundDao.getCollectionByCode(code)
        fund.isCollect = true
        fundDao.update(fund)
        collectionsFund.add(code)
    }

    suspend fun saveOrRemove(isSave: Boolean, code: String) {
        if (isSave)  {
            saveCollection(code)
        } else {
            removeCollection(code)
        }
    }

    private suspend fun removeCollection(code: String) {
        val fund = fundDao.getCollectionByCode(code)
        fund.isCollect = false
        fundDao.update(fund)
        collectionsFund.remove(code)
    }

}