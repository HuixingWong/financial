package com.huixing.financial.ui.collection

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huixing.financial.base.repo.ShareRepo
import com.huixing.financial.model.FundDetail
import com.huixing.financial.network.FinancialService
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.launch

class CollectionViewModel @ViewModelInject constructor(
    val financialService: FinancialService,
    val shareRepo: ShareRepo
) : ViewModel()  {
    private val collectionFundList = MutableLiveData<MutableList<FundDetail>>()

    init {
        fetchAllCollectionData()
    }

    private fun fetchAllCollectionData() {
        viewModelScope.launch {
            shareRepo.collectionsFund.forEach {
                financialService.getFundDetail(it).suspendOnSuccess {
                    data.whatIfNotNull { fundDetail ->
                        collectionFundList.value?.add(fundDetail)
                    }
                }
            }
        }
    }
}