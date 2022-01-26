package com.jccsisc.appnetflixmodule.iu.fragments.home.data

import androidx.lifecycle.Observer
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.appnetflixmodule.iu.fragments.home.model.HomeListMoviesResponse

class HomeInteractor: HomeSource {

    private val localRepository by lazy { HomeRepositoryLocal() }
    private val remoteRepository by lazy { HomeRepositoryRemote() }

    var defaultRemote = true

    override fun getListMovies(path: String, requestModel: RequestModel<Void>, observer: Observer<GenericResponse<HomeListMoviesResponse, String, RequestModel<Void>>>) {
        if (defaultRemote) {
            remoteRepository.getListMovies(path, requestModel, observer)
        } else {
            localRepository.getListMovies(path, requestModel, observer)
        }
    }
}