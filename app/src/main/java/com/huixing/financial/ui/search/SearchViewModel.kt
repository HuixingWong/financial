package com.huixing.financial.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huixing.financial.base.repo.ShareRepo
import com.huixing.financial.model.BaseFundData
import com.huixing.financial.repo.SearchRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
        private val searchRepo: SearchRepo,
        private val shareRepo: ShareRepo
) : ViewModel() {

    val baseFundList = MutableLiveData<List<BaseFundData>>()
    val isLoading = MutableLiveData(false)
    val toast = MutableLiveData<String>()

    fun search(queryFlow: StateFlow<String>) {
        viewModelScope.launch {
            queryFlow.filter {
                if (it.isBlank()){
                    baseFundList.value = shareRepo.mAllFundList
                }
                it.isNotBlank()
            }.onEach {
                isLoading.postValue(true)
            }.flatMapLatest {
                searchRepo.searchFundByKey(it)
            }.catch {
                isLoading.postValue(false)
                toast.postValue(it.message)
            }.collect {
                isLoading.postValue(false)
                if (it.isNullOrEmpty()) {
                    toast.postValue("no data")
                } else {
                    baseFundList.value = it
                }
            }
        }
    }

}