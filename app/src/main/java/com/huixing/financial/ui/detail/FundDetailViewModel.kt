package com.huixing.financial.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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