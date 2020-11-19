package com.huixing.financial.repo

import com.huixing.financial.persistence.FundDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchRepo @Inject constructor(
        private val fundDao: FundDao
){
    suspend fun searchFundByKey(keyWord: String) = flow {
        emit(fundDao.getSearchFundList(keyWord, keyWord))
    }.flowOn(Dispatchers.IO)

}