package com.huixing.financial.ui.rank

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huixing.financial.model.HotFund
import com.huixing.financial.model.request.RankParam
import com.huixing.financial.repo.RankRepo
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RankViewModel @ViewModelInject constructor(
     private  val  rankRepo: RankRepo
): ViewModel() {

    val toast = MutableLiveData<String>()
    val rankData = MutableLiveData<HotFund>()
    val rankParam = MutableLiveData(RankParam())

    fun fetchRankData() {
        viewModelScope.launch {
            rankRepo.fetchRankData(rankParam = rankParam.value!!) {
                toast.postValue(it)
            }.catch {
                toast.postValue(it.message)
            }.collect {
                rankData.value = it
            }
        }
    }

}