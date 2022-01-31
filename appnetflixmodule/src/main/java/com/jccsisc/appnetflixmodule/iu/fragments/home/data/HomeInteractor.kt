package com.jccsisc.appnetflixmodule.iu.fragments.home.data

import androidx.lifecycle.Observer
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.appnetflixmodule.iu.fragments.home.model.HomeListMoviesResponse

class HomeInteractor : UsesCases {

    private val localRepository by lazy { HomeRepositoryLocal() }
    private val remoteRepository by lazy { HomeRepositoryRemote() }

    var defaultRemote = true

    override fun getListMoviesPopulars(
        path: String,
        requestModel: RequestModel<Void>,
        observer: Observer<GenericResponse<HomeListMoviesResponse, String, RequestModel<Void>>>
    ) {
        if (defaultRemote) {
            remoteRepository.getListMoviesPopulars(path, requestModel, observer)
        } else {
            localRepository.getListMoviesPopulars(path, requestModel, observer)
        }
    }

    override fun getListMoviesUpcoming(
        path: String,
        requestModel: RequestModel<Void>,
        observer: Observer<GenericResponse<HomeListMoviesResponse, String, RequestModel<Void>>>
    ) {
        if (defaultRemote) {
            remoteRepository.getListMoviesUpcoming(path, requestModel, observer)
        } else {
            localRepository.getListMoviesUpcoming(path, requestModel, observer)
        }
    }

    override fun getListMoviesTop(
        path: String,
        requestModel: RequestModel<Void>,
        observer: Observer<GenericResponse<HomeListMoviesResponse, String, RequestModel<Void>>>
    ) {
        if (defaultRemote) {
            remoteRepository.getListMoviesTop(path, requestModel, observer)
        } else {
            localRepository.getListMoviesTop(path, requestModel, observer)
        }
    }

    override fun getListMoviesNowPlaying(
        path: String,
        requestModel: RequestModel<Void>,
        observer: Observer<GenericResponse<HomeListMoviesResponse, String, RequestModel<Void>>>
    ) {
        if (defaultRemote) {
            remoteRepository.getListMoviesNowPlaying(path, requestModel, observer)
        } else {
            localRepository.getListMoviesNowPlaying(path, requestModel, observer)
        }
    }
}