package com.jccsisc.appnetflixmodule.iu.fragments.fragmentsPager.reviews

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jccsisc.appnetflixmodule.R
import com.jccsisc.appnetflixmodule.databinding.ReviewsFragmentBinding

class ReviewsFragment(val idMovie: Int): Fragment(R.layout.reviews_fragment) {

    lateinit var mBinding: ReviewsFragmentBinding
    val viewModel by viewModels<ReviewsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mBinding = ReviewsFragmentBinding.bind(view)
        super.onViewCreated(mBinding.root, savedInstanceState)

        initElements()
        initObserversReviews()
    }
}