package com.huixing.financial.ui.collection

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huixing.financial.model.BaseDetail
import com.huixing.financial.repo.SimpleCollectionRepo
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SimpleCollectionViewModel @Inject constructor(
    private val simpleCollectionRepo: SimpleCollectionRepo,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val collectionFundData = savedStateHandle.getLiveData<BaseDetail>("collection_data")
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val toastData: MutableLiveData<String> = MutableLiveData()

    init {
        fetchAllCollectionData()
    }

    fun fetchAllCollectionData() {
        isLoading.value = true
        viewModelScope.launch {
            simpleCollectionRepo.fetchAllCollectionData {
                toastData.postValue(it)
            }.catch {
                toastData.postValue(it.message)
            }.onCompletion {
                isLoading.postValue(false)
            }.collect {
                savedStateHandle.set("collection_data", it)
            }
        }
    }
}