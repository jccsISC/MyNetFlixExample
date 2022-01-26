package com.jccsisc.appnetflixmodule.iu.fragments.information

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jccsisc.appnetflixmodule.databinding.BottomSheetDetailBinding
import com.jccsisc.appnetflixmodule.iu.fragments.information.model.InformationModel

class BottomSheetDetailFragment(val info: InformationModel): BottomSheetDialogFragment() {

    lateinit var mBinding: BottomSheetDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = BottomSheetDetailBinding.inflate(layoutInflater)
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initElements()
    }

}