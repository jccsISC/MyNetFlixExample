package com.jccsisc.appnetflixmodule.iu.fragments.fragmentsPager.frames

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jccsisc.appnetflixmodule.R
import com.jccsisc.appnetflixmodule.databinding.FrameFragmentBinding

class FrameFragment(val idMovie: Int) : Fragment(R.layout.frame_fragment) {

    lateinit var mBinding: FrameFragmentBinding
    val viewModel by viewModels<FrameViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mBinding = FrameFragmentBinding.bind(view)
        super.onViewCreated(mBinding.root, savedInstanceState)

        initElements()
        initObserversFrame()
    }
}