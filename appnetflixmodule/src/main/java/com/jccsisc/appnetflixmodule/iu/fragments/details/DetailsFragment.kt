package com.jccsisc.appnetflixmodule.iu.fragments.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.jccsisc.appnetflixmodule.R
import com.jccsisc.appnetflixmodule.databinding.DetailsFragmentBinding

class DetailsFragment : Fragment(R.layout.details_fragment) {

    lateinit var mBinding: DetailsFragmentBinding
    val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mBinding = DetailsFragmentBinding.bind(view)
        super.onViewCreated(mBinding.root, savedInstanceState)

        initElements()
    }
}