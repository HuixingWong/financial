package com.huixing.financial.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huixing.financial.model.HotFund
import com.huixing.financial.repo.MainRepo
import com.huixing.financial.utils.event.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepo: MainRepo
) : ViewModel() {

    var hotFundData = MutableLiveData<HotFund>()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val toastData: SingleLiveEvent<String> = SingleLiveEvent()
    var scrollPosition: Int = 0

    init {
        fetchHotData()
    }

    fun saveState(position: Int){
        scrollPosition = position
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