package com.jccsisc.appnetflixmodule.iu.fragments.details.fragmentsPager.about

import com.jccsisc.appnetflixmodule.R
import com.jccsisc.appnetflixmodule.utils.ConstantesObject.english
import com.jccsisc.appnetflixmodule.utils.ConstantesObject.hindi
import com.jccsisc.appnetflixmodule.utils.ConstantesObject.japanese
import com.jccsisc.appnetflixmodule.utils.ConstantesObject.korean
import com.jccsisc.appnetflixmodule.utils.ConstantesObject.spanish

fun AboutFragment.initElements() {
    mBinding.apply {
        data.apply {

            tvRating.text = getString(R.string.raiting, vote_average.toString())

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