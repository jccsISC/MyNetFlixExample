package com.jccsisc.appnetflixmodule.iu.fragments.fragmentsPager.about

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.jccsisc.appnetflixmodule.R
import com.jccsisc.appnetflixmodule.databinding.AboutFragmentBinding
import com.jccsisc.appnetflixmodule.iu.fragments.home.model.HomeMovieResponse

class AboutFragment(val data: HomeMovieResponse) : Fragment(R.layout.about_fragment) {
    lateinit var mBinding: AboutFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mBinding = AboutFragmentBinding.bind(view)
        super.onViewCreated(mBinding.root, savedInstanceState)

        initElements()
    }
}