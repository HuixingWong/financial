package com.huixing.financial.ui.rank

import androidx.core.view.forEach
import androidx.fragment.app.activityViewModels
import com.google.android.material.chip.Chip
import com.huixing.financial.R
import com.huixing.financial.base.DataBindingDialogFragment
import com.huixing.financial.databinding.FragmentRankDialogBindingImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_rank_dialog.*

@AndroidEntryPoint
class RankDialogFragment : DataBindingDialogFragment<FragmentRankDialogBindingImpl>(
    R.layout.fragment_rank_dialog
) {

    private val rankViewModel: RankViewModel by activityViewModels()

    override fun bind() {
        binding?.apply {
            vm = rankViewModel
            submit.setOnClickListener {
                submitData()
                dismiss()
            }
            root.post {
                bindView()
            }
        }
    }

    private fun bindView() {
        mTypeGroup?.forEach {
            (it as? Chip)?.apply {
                if (tag != null &&
                    rankViewModel.rankParam.fundType?.contains(tag) == true
                ) {
                    isChecked = true
                }
            }
        }
        mCompanyGroup?.forEach {
            (it as? Chip)?.apply {
                if (tag != null &&
                    rankViewModel.rankParam.fundCompany?.contains(tag) == true
                ) {
                    isChecked = true
                }
            }
        }
        mSortGroup?.forEach {
            (it as? Chip)?.apply {
                if (tag != null &&
                    rankViewModel.rankParam.sort == tag
                ) {
                    isChecked = true
                }
            }
        }
        useDesc.isChecked = rankViewModel.rankParam.asc != 0
        dataSize.setText(rankViewModel.rankParam.pageSize.toString())
    }

    private fun submitData() {
        val typeList = mutableListOf<String>()
        mTypeGroup.checkedChipIds.forEach {
            (view?.findViewById<Chip>(it)?.tag as? String)?.let { type ->
                typeList.add(type)
            }
        }
        val companyList = mutableListOf<String>()
        mCompanyGroup.checkedChipIds.forEach {
            (view?.findViewById<Chip>(it)?.tag as? String)?.let { comp ->
                companyList.add(comp)
            }
        }
        rankViewModel.rankParam.fundType = typeList
        rankViewModel.rankParam.fundCompany = companyList
        rankViewModel.rankParam.sort = view?.findViewById<Chip>(
                mSortGroup.checkedChipId
        )?.tag as? String
        rankViewModel.rankParam.asc = if (useDesc.isChecked) 1 else 0
        rankViewModel.rankParam.pageSize = dataSize.text.toString().toInt()
        rankViewModel.fetchRankData()
    }

}