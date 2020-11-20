package com.huixing.financial.repo

import android.annotation.SuppressLint
import com.huixing.financial.R
import com.huixing.financial.network.FinancialService
import com.huixing.financial.utils.log
import com.huixing.financial.utils.toDate
import com.huixing.financial.utils.toStrDate
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.time.LocalDate
import javax.inject.Inject

class FundDetailRepo @Inject constructor(
        private val financialService: FinancialService,
) {

    private val fundMap = mutableMapOf<String, String>()
    lateinit var mCurrentDate: String
    lateinit var mEndDate: LocalDate

    suspend fun fetchFundDetail(
            code: String,
            startDate: String? = null,
            endDate: String? = null,
            onError: (String) -> Unit,
    ) = flow {
        financialService.getFundDetail(code, startDate, endDate)
                .suspendOnSuccess {
                    emit(this.data?.data)
                    this.data?.data?.netWorthData?.forEach {
                        fundMap[it[0]] = it[1]
                    }
                }.onError {
                    onError(this.statusCode.code.toString())
                }.onException {
                    onError(this.message.toString())
                }
    }.flowOn(Dispatchers.IO)

    /**
     * this method return a pair
     * first is ratio, second is last money
     */
    @SuppressLint("NewApi")
    suspend fun calculateByPlan(
            id: Int,
            startDate: String,
            endDate: String?,
            inputMoney: String,
            onError: (String) -> Unit,
    ) = flow {
        mCurrentDate = startDate
        mEndDate = endDate?.toDate() ?: LocalDate.now().minusDays(1)
        checkDate(startDate, endDate, onError).collect {
            var resultMoney = inputMoney.toDouble()
            var unitMoney = resultMoney
            var totalPut = resultMoney
            while (mCurrentDate.toDate().isBefore(mEndDate)) {
                val nextRatio = getNextRatio(id) ?: break
                resultMoney = resultMoney.times(nextRatio)
                if (mCurrentDate.toDate().isBefore(mEndDate)) {
                    resultMoney += unitMoney
                    totalPut += unitMoney
                }
            }
            emit(Pair(totalPut, resultMoney))
        }
    }.flowOn(Dispatchers.IO)


    @SuppressLint("NewApi")
    fun getNextRatio(id: Int): Double? {

        val valueStart = getAvailableDateLatest(mCurrentDate)
        mCurrentDate = when (id) {
            R.id.week -> {
                mCurrentDate.toDate().plusWeeks(1)
            }
            R.id.month -> {
                mCurrentDate.toDate().plusMonths(1)
            }
            R.id.year -> {
                mCurrentDate.toDate().plusYears(1)
            }
            else -> LocalDate.MIN
        }.toStrDate()
        log(message = "🧮得到的 $mCurrentDate")
        val valueEnd = getAvailableDateLatest(mCurrentDate)
        log("" +
                "=====================" +
                "")
        return if (valueStart == null || valueEnd == null) {
            null
        } else {
            valueEnd.toDouble().div(valueStart.toDouble())
        }
    }

    @SuppressLint("NewApi")
    fun getAvailableDateLatest(currentDate: String): String? {
        var available = currentDate
        while (!fundMap.containsKey(available)) {
            available = when {
                available.toDate().isBefore(mEndDate) -> {
                    available.toDate().plusDays(1).toString()
                }
                available.toDate().isEqual(mEndDate) -> {
                    getBeforeDayUntilAvailable(available)
                }
                else -> {
                    available.toDate().minusDays(1).toString()
                }
            }
        }
        log("🉑️可用的： $available")
        return fundMap[available]
    }

    @SuppressLint("NewApi")
    fun getBeforeDayUntilAvailable(currentDate: String): String {
        var date = currentDate
        while (!fundMap.containsKey(date)) {
            date = date.toDate().minusDays(1).toStrDate()
        }
        return date
    }


    suspend fun calculate(
            startDate: String?,
            endDate: String?,
            onError: (String) -> Unit,
    ) =
            flow {
                checkDate(startDate, endDate, onError).collect {
                    emit(it.second / it.first)
                }
            }.flowOn(Dispatchers.IO)


    private fun checkDate(
            startDate: String?,
            endDate: String?,
            onError: (String) -> Unit,
    ) =
            flow {
                val startValue = fundMap[startDate]?.toDouble() ?: -1.0
                val endValue = fundMap[endDate]?.toDouble() ?: -1.0
                if (startValue < 0) {
                    onError("开始日期有误")
                    return@flow
                }
                if (endValue < 0) {
                    onError("结束日期日期有误")
                    return@flow
                }
                emit(Pair(startValue, endValue))
            }

}