package com.huixing.financial.ui.collection

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huixing.financial.model.BaseDetail
import com.huixing.financial.repo.SimpleCollectionRepo
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

class SimpleCollectionViewModel @ViewModelInject constructor(
    private val simpleCollectionRepo: SimpleCollectionRepo
) : ViewModel() {

    val collectionFundData = MutableLiveData<BaseDetail>()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val toastData: MutableLiveData<String> = MutableLiveData()

    init {
        fetchAllCollectionData()
    }

    fun fetchAllCollectionData() {
        isLoading.value = true
        viewModelScope.launch {
            simpleCollectionRepo.fetchAllCollectionData().catch {
                toastData.postValue(it.message)
            }.onCompletion {
                isLoading.postValue(false)
            }.collect {
                collectionFundData.value = it
            }
        }
    }
}