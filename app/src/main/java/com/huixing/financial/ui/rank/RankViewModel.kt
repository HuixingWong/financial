package com.huixing.financial.ui.rank

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huixing.financial.model.HotFund
import com.huixing.financial.model.request.RankParam
import com.huixing.financial.repo.RankRepo
import com.huixing.financial.utils.log
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

class RankViewModel @ViewModelInject constructor(
     private  val  rankRepo: RankRepo
): ViewModel() {

    val toast = MutableLiveData<String>()
    val rankData = MutableLiveData<HotFund>()
    val loading = MutableLiveData(false)
    val rankParam = MutableLiveData(RankParam())


    init {
        fetchRankData()
    }

    fun fetchRankData() {
        viewModelScope.launch {
            loading.value = true
            rankRepo.fetchRankData(rankParam = rankParam.value!!) {
                toast.postValue(it)
            }.catch {
                toast.postValue(it.message)
                it.message?.let { it1 -> log(message = it1) }
            }.onCompletion {
                loading.value = false
            }.collect {
                rankData.value = it
            }
        }
    }

}