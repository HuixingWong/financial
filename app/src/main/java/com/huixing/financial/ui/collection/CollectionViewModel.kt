package com.huixing.financial.ui.collection

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huixing.financial.model.FundDetail
import com.huixing.financial.repo.CollectionRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
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
            collectionRepo.fetchAllCollectionData {
                toastData.postValue(it)
            }.catch {
                toastData.postValue(it.message)
            }.onCompletion {
                isLoading.postValue(false)
            }.collect {
                collectionFundList.value = it
            }
        }
    }
}