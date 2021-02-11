package com.huixing.financial.ui.rank

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huixing.financial.model.HotFund
import com.huixing.financial.model.request.RankParam
import com.huixing.financial.repo.RankRepo
import com.huixing.financial.utils.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RankViewModel @Inject constructor(
     private  val  rankRepo: RankRepo
): ViewModel() {

    val toast = MutableLiveData<String>()
    val rankData = MutableLiveData<HotFund>()
    val loading = MutableLiveData(false)
    var rankParam = RankParam()

    init {
        fetchRankData()
    }

    fun submit(
            typeList: MutableList<String>,
            companyList: MutableList<String>,
            sort: String?, asc: Int, pageSize: Int
    ) {
        rankParam.apply {
            fundType = typeList
            fundCompany = companyList
            this.sort = sort
            this.asc = asc
            this.pageSize = pageSize
        }
        fetchRankData()
    }

    fun fetchRankData() {
        viewModelScope.launch {
            loading.value = true
            rankRepo.fetchRankData(rankParam = rankParam) {
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