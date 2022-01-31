package com.jccsisc.appnetflixmodule.iu.fragments.fragmentsPager.frames.expand

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.jccsisc.appnetflixmodule.R
import com.jccsisc.appnetflixmodule.databinding.ExpandFragmentBinding

class ExpandFragment : Fragment(R.layout.expand_fragment) {
    lateinit var mBinding: ExpandFragmentBinding
    val args by navArgs<ExpandFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mBinding = ExpandFragmentBinding.bind(view)
        super.onViewCreated(mBinding.root, savedInstanceState)

       mBinding.apply {

           btnBack.setOnClickListener { findNavController().popBackStack() }

           Glide.with(this@ExpandFragment)
               .load(getString(R.string.url_img, args.image))
               .centerInside()
               .into(imgExpand)
       }
    }
}