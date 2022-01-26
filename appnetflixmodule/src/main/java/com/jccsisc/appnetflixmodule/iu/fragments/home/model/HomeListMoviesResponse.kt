package com.jccsisc.appnetflixmodule.iu.fragments.home.model

import com.google.gson.annotations.SerializedName

data class HomeListMoviesResponse(
        @SerializedName("results")
        val listMovies: List<HomeMovieResponse> = listOf()
)