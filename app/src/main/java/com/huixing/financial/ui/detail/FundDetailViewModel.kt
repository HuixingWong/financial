package com.huixing.financial.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.huixing.financial.R
import com.huixing.financial.model.FundDetailData
import com.huixing.financial.repo.FundDetailRepo
import com.huixing.financial.utils.toStrDate
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import java.time.LocalDate

class FundDetailViewModel @AssistedInject constructor(
        private val fundDetailRepo: FundDetailRepo,
        @Assisted private val fundCode: String
) : ViewModel() {

    val fundDetailData = MutableLiveData<FundDetailData>()
    val isLoading = MutableLiveData<Boolean>()
    val toast = MutableLiveData<String>()

    val startDate: MutableLiveData<String>
            = MutableLiveData(LocalDate.now().minusMonths(1).toStrDate())

    val endDate: MutableLiveData<String> =
        MutableLiveData(LocalDate.now().minusDays(1).toStrDate())

    val inputMoney: MutableLiveData<String> = MutableLiveData(100.toString())

    val radioId: MutableLiveData<Int> = MutableLiveData(R.id.week)
    val usePlan: MutableLiveData<Boolean> = MutableLiveData(false)

    val resultMoney: MutableLiveData<String> = MutableLiveData()
    val resultRatio: MutableLiveData<String> = MutableLiveData()

    val putAllMoney: MutableLiveData<String> = MutableLiveData()


    init {
        fetchFundDetail(fundCode)
    }

    fun submitEvaluate() {

        if (startDate.value == null) {
            toast.postValue("请选择开始日期")
            return
        }

        if (usePlan.value == false) {
            calculate()
        } else {
            dispatchPlan()
        }

    }

    private fun dispatchPlan() {
        if (inputMoney.value == null) {
            toast.value = "请输入定投金额"
        }
        viewModelScope.launch {
            radioId.value?.let {
                isLoading.postValue(true)
                fundDetailRepo.calculateByPlan(
                        it, startDate.value!!, endDate.value, inputMoney.value!!) { error ->
                    toast.postValue(error)
                }.catch { error ->
                    toast.postValue(error.message)
                }.onCompletion {
                    isLoading.postValue(false)
                }.collect { result ->
                    putAllMoney.value = result.first.toString()
                    resultMoney.value = result.second.toString()
                    resultRatio.value = (result.second / result.first).toString()
                }
            }
        }
    }

    /**
     * 需要处理用户选择的日期是节假日的情况， 查找下一个工作日， 或者下一个有数据的日期
     *
     * 手续费无法计算， 购买日的手续费没有数据
     */
    private fun calculate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            fundDetailRepo.calculate(startDate.value,
                    endDate.value) {
                toast.postValue(it)
            }.catch {
                toast.postValue(it.message)
            }.onCompletion {
                isLoading.postValue(false)
            }.collect {
                resultRatio.value = it.toString()
                if (!inputMoney.value.isNullOrBlank()) {
                    resultMoney.value = inputMoney.value?.toDouble()?.times(it).toString()
                }
            }
        }
    }


    private fun fetchFundDetail(code: String, start: String? = null, end: String? = null) {

        viewModelScope.launch {
            isLoading.postValue(true)
            fundDetailRepo.fetchFundDetail(code, start, end) {
                toast.postValue(it)
            }.catch {
                toast.postValue(it.message)
            }.onCompletion {
                isLoading.postValue(false)
            }.collect {
                fundDetailData.postValue(it)
            }
        }

    }

    @AssistedInject.Factory
    interface AssistedFactory {
        fun create(fundCode: String): FundDetailViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            fundCode: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(fundCode) as T
            }
        }
    }

}