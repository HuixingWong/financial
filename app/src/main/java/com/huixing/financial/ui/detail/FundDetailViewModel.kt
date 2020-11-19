package com.huixing.financial.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huixing.financial.R
import com.huixing.financial.model.FundDetailData
import com.huixing.financial.repo.FundDetailRepo
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

class FundDetailViewModel @ViewModelInject constructor(
    private val fundDetailRepo: FundDetailRepo
) : ViewModel() {

    val fundDetailData = MutableLiveData<FundDetailData>()
    val isLoading = MutableLiveData<Boolean>()
    val toast = MutableLiveData<String>()

    val startDate: MutableLiveData<String> = MutableLiveData()
    val endDate: MutableLiveData<String> = MutableLiveData()
    val inputMoney: MutableLiveData<String> = MutableLiveData()

    val radioId: MutableLiveData<Int> = MutableLiveData(R.id.week)
    val usePlan: MutableLiveData<Boolean> = MutableLiveData(false)

    val resultMoney: MutableLiveData<String> = MutableLiveData()
    val resultRatio: MutableLiveData<String> = MutableLiveData()

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
        when (radioId.value) {
            R.id.week -> {

            }
            R.id.month -> {

            }
            R.id.year -> {

            }
        }
    }

    private fun calculateByPlan() {


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


    fun fetchFundDetail(code: String, start: String? = null, end: String? = null) {

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


}