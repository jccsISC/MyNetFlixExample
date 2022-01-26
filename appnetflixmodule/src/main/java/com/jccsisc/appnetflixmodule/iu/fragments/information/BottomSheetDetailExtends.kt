package com.jccsisc.appnetflixmodule.iu.fragments.information

import com.bumptech.glide.Glide
import com.jccsisc.appnetflixmodule.R
import com.jccsisc.appnetflixmodule.utils.ConstantesObject.english
import com.jccsisc.appnetflixmodule.utils.ConstantesObject.hindi
import com.jccsisc.appnetflixmodule.utils.ConstantesObject.japanese
import com.jccsisc.appnetflixmodule.utils.ConstantesObject.korean
import com.jccsisc.appnetflixmodule.utils.ConstantesObject.spanish

fun BottomSheetDetailFragment.initElements() {
    mBinding.apply {

        imgClose.setOnClickListener { dismiss() }

        info.apply {
            Glide.with(root.context)
                .load(root.context.getString(R.string.url_img, image))
                .centerCrop()
                .into(mBinding.imgMovieDetail)

            tvTitleDetail.text = title
            tvRating.text = rating
            when(language) {
                "en" ->  tvLanguaje.text = getString(R.string.language, english)
                "es" ->  tvLanguaje.text = getString(R.string.language, spanish)
                "hi" ->  tvLanguaje.text = getString(R.string.language, hindi)
                "ja" ->  tvLanguaje.text = getString(R.string.language, japanese)
                "ko" ->  tvLanguaje.text = getString(R.string.language, korean)
            }
            tvDescription.text = description
        }

    }
}