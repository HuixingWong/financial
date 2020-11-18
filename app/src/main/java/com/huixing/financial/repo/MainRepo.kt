package com.huixing.financial.repo

import android.util.Log
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

class MainRepo @Inject constructor(
    private val financialService: FinancialService,
    ) {

    suspend fun fetchHotFund(onSuccess: () -> Unit, onError: (String) -> Unit) = flow {
        financialService.getAllData().suspendOnSuccess {
            data.whatIfNotNull { response ->
                if (response.code == 200) {
                    emit(response)
                }
                onSuccess()
            }
        }.onError {
            onError(message())
        }.onException {
            onError(message())
        }
    }.flowOn(Dispatchers.IO)
}