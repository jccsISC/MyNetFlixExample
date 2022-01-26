package com.jccsisc.appnetflixmodule.iu.fragments.home.data

import androidx.lifecycle.Observer
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.appnetflixmodule.iu.fragments.home.model.HomeListMoviesResponse

interface HomeSource {
    fun getListMovies(
        paht: String,
        requestModel: RequestModel<Void>,
        observer: Observer<GenericResponse<HomeListMoviesResponse, String, RequestModel<Void>>>
    )
}