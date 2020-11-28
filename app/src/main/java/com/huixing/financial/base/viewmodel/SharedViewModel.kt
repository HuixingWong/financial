package com.huixing.financial.base.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huixing.financial.base.repo.AnalyseRepo
import com.huixing.financial.base.repo.ShareRepo
import com.huixing.financial.model.Rank
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SharedViewModel @ViewModelInject constructor(
    private val shareRepo: ShareRepo,
    private val analyseRepo: AnalyseRepo
) : ViewModel() {

    val showSearch = MutableLiveData(false)
    private val collectionInitSuccess = MutableLiveData(false)

    init {
        syncAllFundData()
        fetchAllCollection()
    }

    private fun syncAllFundData() {
        viewModelScope.launch {
            shareRepo.getAllDataAndSave(onSuccess = {
                showSearch.postValue(true)
            }, onError = {
                showSearch.postValue(false)
            }).collect {
                showSearch.value = it
            }
        }
    }

    fun analyseRankData(rank: List<Rank>) {
        viewModelScope.launch {
            analyseRepo.analyse(rank)
        }
    }

    fun addCollection(code: String) {
        viewModelScope.launch {
            shareRepo.saveCollection(code)
        }
    }

    fun removeCollection(code: String) {
        viewModelScope.launch {
            shareRepo.removeCollection(code)
        }
    }

    private fun fetchAllCollection() {
        viewModelScope.launch {
            shareRepo.getAllCollectionFund().collect {
                collectionInitSuccess.postValue(true)
            }
        }
    }

}