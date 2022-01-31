package com.jccsisc.appnetflixmodule.iu.fragments.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jccsisc.appnetflixmodule.R
import com.jccsisc.appnetflixmodule.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var mBinding: FragmentHomeBinding
    val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mBinding = FragmentHomeBinding.bind(view)
        super.onViewCreated(mBinding.root, savedInstanceState)
        initElements()
        initObserverMovies()
    }
}