package com.jccsisc.appnetflixmodule.iu.fragments.fragmentsPager.about

import com.jccsisc.appnetflixmodule.R
import com.jccsisc.appnetflixmodule.utils.ConstantsObject.english
import com.jccsisc.appnetflixmodule.utils.ConstantsObject.hindi
import com.jccsisc.appnetflixmodule.utils.ConstantsObject.japanese
import com.jccsisc.appnetflixmodule.utils.ConstantsObject.korean
import com.jccsisc.appnetflixmodule.utils.ConstantsObject.spanish

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