package com.jccsisc.appnetflixmodule.iu.fragments.home.data

import androidx.lifecycle.Observer
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.appnetflixmodule.iu.fragments.home.model.HomeListMoviesResponse

class HomeRepositoryLocal : UsesCases {

    override fun getListMoviesPopulars(
        path: String,
        requestModel: RequestModel<Void>,
        observer: Observer<GenericResponse<HomeListMoviesResponse, String, RequestModel<Void>>>
    ) {

    }

    override fun getListMoviesUpcoming(
        path: String,
        requestModel: RequestModel<Void>,
        observer: Observer<GenericResponse<HomeListMoviesResponse, String, RequestModel<Void>>>
    ) {

    }

    override fun getListMoviesTop(
        path: String,
        requestModel: RequestModel<Void>,
        observer: Observer<GenericResponse<HomeListMoviesResponse, String, RequestModel<Void>>>
    ) {

    }

    override fun getListMoviesNowPlaying(
        path: String,
        requestModel: RequestModel<Void>,
        observer: Observer<GenericResponse<HomeListMoviesResponse, String, RequestModel<Void>>>
    ) {

    }
}