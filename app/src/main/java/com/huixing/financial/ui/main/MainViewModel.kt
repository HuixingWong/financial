package com.huixing.financial.ui.main

import androidx.databinding.ObservableBoolean
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
    val isLoading: ObservableBoolean = ObservableBoolean(false)
    val toastData: MutableLiveData<String> = MutableLiveData()

    fun fetchHotData() {
        isLoading.set(true)
        viewModelScope.launch {
            mainRepo.fetchHotFund({
            }, {
                toastData.postValue(it)
            }).collect {
                isLoading.set(false)
                hotFundData.value = it
            }
        }
    }
}