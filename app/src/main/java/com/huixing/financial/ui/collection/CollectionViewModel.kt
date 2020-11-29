package com.huixing.financial.ui.collection

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huixing.financial.model.FundDetail
import com.huixing.financial.network.FinancialService
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

class CollectionViewModel @ViewModelInject constructor(
    val financialService: FinancialService,
    private val collectionRepo: CollectionRepo
) : ViewModel() {
    val collectionFundList = MutableLiveData<MutableList<FundDetail>>()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val toastData: MutableLiveData<String> = MutableLiveData()

    init {
        fetchAllCollectionData()
    }

    fun fetchAllCollectionData() {
        isLoading.value = true
        viewModelScope.launch {
            collectionRepo.fetchAllCollectionData().onCompletion {
                isLoading.postValue(false)
            }.collect {
                collectionFundList.value = it
            }
        }
    }
}