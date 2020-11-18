package com.huixing.financial.base.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huixing.financial.base.repo.ShareRepo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SharedViewModel @ViewModelInject constructor(
        private val shareRepo: ShareRepo
): ViewModel(){

    val showSearch = MutableLiveData(false)

    fun syncAllFundData() {
        viewModelScope.launch {
            shareRepo.getAllDataAndSave(onSuccess = {
                showSearch.postValue(true)
            }, onError = {
                showSearch.postValue(false)
            }).collect {
                showSearch.value = true
            }
        }
    }

}