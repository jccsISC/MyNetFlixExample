package com.jccsisc.appnetflixmodule.iu.fragments.home

import androidx.lifecycle.ViewModel
import com.jccsisc.appnetflixmodule.common.core.request.GenericRequest
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.appnetflixmodule.common.core.status.StatusRequestEnum
import com.jccsisc.appnetflixmodule.iu.fragments.home.data.HomeInteractor
import com.jccsisc.appnetflixmodule.iu.fragments.home.model.HomeListMoviesResponse

class HomeViewModel: ViewModel() {

    private val interactor = HomeInteractor()
    val responseListMovies: GenericRequest<GenericResponse<HomeListMoviesResponse, String, RequestModel<Void>>> = GenericRequest()

    fun requestData(path: String, requestModel: RequestModel<Void>) {
        responseListMovies.postValue(GenericResponse(StatusRequestEnum.LOADING, requestData = requestModel))
        interactor.getListMovies( path, requestModel) {
            responseListMovies.postValue(
                it
            )
        }
    }
}