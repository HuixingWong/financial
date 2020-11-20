package com.huixing.financial.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huixing.financial.model.HotFund
import com.huixing.financial.repo.MainRepo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val mainRepo: MainRepo
) : ViewModel() {

    var hotFundData = MutableLiveData<HotFund>()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val toastData: MutableLiveData<String> = MutableLiveData()

    init {
        fetchHotData()
    }

    fun fetchHotData() {
        isLoading.postValue(true)
        viewModelScope.launch {
            mainRepo.fetchHotFund({
            }, {
                isLoading.postValue(false)
                toastData.postValue(it)
            }).collect {
                isLoading.postValue(false)
                hotFundData.value = it
            }
        }
    }
}