package com.huixing.financial.ui.rank

import androidx.core.view.forEach
import androidx.fragment.app.activityViewModels
import com.google.android.material.chip.Chip
import com.huixing.financial.R
import com.huixing.financial.base.DataBindingDialogFragment
import com.huixing.financial.databinding.FragmentRankDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RankDialogFragment : DataBindingDialogFragment<FragmentRankDialogBinding>(
        R.layout.fragment_rank_dialog
) {

    private val rankViewModel: RankViewModel by activityViewModels()

    override fun bind() {
        binding?.apply {
            vm = rankViewModel
            root.post {
                bindView()
            }
        }
    }

    private fun bindView() {

        binding?.submit?.setOnClickListener {
            submitData()
            dismiss()
        }

        binding?.mTypeGroup?.forEach {
            (it as? Chip)?.apply {
                if (tag != null &&
                        rankViewModel.rankParam.fundType?.contains(tag) == true
                ) {
                    isChecked = true
                }
            }
        }
        binding?.mCompanyGroup?.forEach {
            (it as? Chip)?.apply {
                if (tag != null &&
                        rankViewModel.rankParam.fundCompany?.contains(tag) == true
                ) {
                    isChecked = true
                }
            }
        }
        binding?.mSortGroup?.forEach {
            (it as? Chip)?.apply {
                if (tag != null &&
                        rankViewModel.rankParam.sort == tag
                ) {
                    isChecked = true
                }
            }
        }
        binding?.useDesc?.isChecked = rankViewModel.rankParam.asc != 0
        binding?.dataSize?.setText(rankViewModel.rankParam.pageSize.toString())
    }

    private fun submitData() {
        val typeList = mutableListOf<String>()
        binding?.mTypeGroup?.checkedChipIds?.forEach {
            (view?.findViewById<Chip>(it)?.tag as? String)?.let { type ->
                typeList.add(type)
            }
        }
        val companyList = mutableListOf<String>()
        binding?.mCompanyGroup?.checkedChipIds?.forEach {
            (view?.findViewById<Chip>(it)?.tag as? String)?.let { comp -> companyList.add(comp) }
        }
        rankViewModel.rankParam.fundType = typeList
        rankViewModel.rankParam.fundCompany = companyList
        rankViewModel.rankParam.sort =
                binding?.mSortGroup?.checkedChipId?.let {
                    view?.findViewById<Chip>(it)?.tag
                } as? String
        rankViewModel.rankParam.asc = if (binding?.useDesc?.isChecked == true) 1 else 0
        rankViewModel.rankParam.pageSize = binding?.dataSize?.text.toString().toInt()
        rankViewModel.fetchRankData()
    }

}