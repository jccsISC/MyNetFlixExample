package com.jccsisc.appnetflixmodule.iu.fragments.information

import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.jccsisc.appnetflixmodule.R
import com.jccsisc.appnetflixmodule.iu.fragments.home.HomeFragmentDirections
import com.jccsisc.appnetflixmodule.utils.ConstantsObject.english
import com.jccsisc.appnetflixmodule.utils.ConstantsObject.hindi
import com.jccsisc.appnetflixmodule.utils.ConstantsObject.japanese
import com.jccsisc.appnetflixmodule.utils.ConstantsObject.korean
import com.jccsisc.appnetflixmodule.utils.ConstantsObject.spanish

fun BottomSheetDetailFragment.initElements() {
    mBinding.apply {

        imgClose.setOnClickListener { dismiss() }

        btnDetail.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(info)
            findNavController().navigate(action)
            dismiss()
        }

        info.apply {
            Glide.with(root.context)
                .load(root.context.getString(R.string.url_img, backdrop_path))
                .centerCrop()
                .into(mBinding.imgMovieDetail)

            tvTitleDetail.text = title
            tvRating.text = vote_count.toString()
            when(original_language) {
                "en" ->  tvLanguaje.text = getString(R.string.language, english)
                "es" ->  tvLanguaje.text = getString(R.string.language, spanish)
                "hi" ->  tvLanguaje.text = getString(R.string.language, hindi)
                "ja" ->  tvLanguaje.text = getString(R.string.language, japanese)
                "ko" ->  tvLanguaje.text = getString(R.string.language, korean)
            }
            tvDescription.text = overview
        }

    }
}