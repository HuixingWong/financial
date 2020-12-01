package com.huixing.financial.repo

import com.huixing.financial.model.request.RankParam
import com.huixing.financial.network.FinancialService
import com.huixing.financial.utils.rankParamAdapter
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RankRepo @Inject constructor(
    private val financialService: FinancialService
) {

    suspend fun fetchRankData(rankParam: RankParam, onError: (String) -> Unit)
            = flow {
        val paramString = rankParamAdapter.toJson(rankParam)
        financialService.fetchRankData(paramString).suspendOnSuccess {
            data.whatIfNotNull {
                if (it.code == 200) {
                    emit(it)
                } else {
                    onError(it.message.toString())
                }
            }
        }.onError {
            onError(message())
        }
    }

}