package com.huixing.financial.repo

import com.huixing.financial.model.request.RankParam
import com.huixing.financial.network.FinancialService
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RankRepo @Inject constructor(
        private val financialService: FinancialService
) {

    suspend  fun fetchRankData(rankParam: RankParam, onError: (String) -> Unit) = flow {
        financialService.fetchRankData(rankParam).suspendOnSuccess {
            data.whatIfNotNull {
                emit(it)
            }
        }.onError {
            onError(message())
        }
    }

}