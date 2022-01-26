package com.jccsisc.appnetflixmodule.iu.fragments.details

import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.jccsisc.appnetflixmodule.R
import com.jccsisc.appnetflixmodule.common.MEnumsTitles
import com.jccsisc.appnetflixmodule.iu.fragments.details.adapter.ViewPagerAdapter
import com.jccsisc.appnetflixmodule.iu.fragments.details.fragmentsPager.about.AboutFragment
import com.jccsisc.appnetflixmodule.iu.fragments.details.fragmentsPager.frames.FrameFragment
import com.jccsisc.appnetflixmodule.iu.fragments.details.fragmentsPager.reviews.ReviewsFragment
import com.jccsisc.appnetflixmodule.utils.MLambdasObject

fun DetailsFragment.initElements() {
    mBinding.apply {

        MLambdasObject.changeTitle?.invoke(MEnumsTitles.DETAILS.title)

        btnBack.setOnClickListener { findNavController().popBackStack() }


        args.modelMovie.apply {
            Glide.with(this@initElements)
                .load(getString(R.string.url_img, backdrop_path))
                .into(imgMovie)

            Glide.with(this@initElements)
                .load(getString(R.string.url_img, poster_path))
                .into(imgMovieDetail)

            txtTitle.text = title

            val fragmentAdapter = ViewPagerAdapter(childFragmentManager)
            fragmentAdapter.addFragment(AboutFragment(args.modelMovie), getString(R.string.about))
            fragmentAdapter.addFragment(ReviewsFragment(id), getString(R.string.reviews))
            fragmentAdapter.addFragment(FrameFragment(id), getString(R.string.images))
            viewPagerDetails.adapter = fragmentAdapter
            tabLayoutDetails.setupWithViewPager(viewPagerDetails)
        }
    }
}