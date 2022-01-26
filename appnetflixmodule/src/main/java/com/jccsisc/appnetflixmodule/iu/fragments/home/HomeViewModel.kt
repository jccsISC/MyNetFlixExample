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
    val responseListMoviesPopular: GenericRequest<GenericResponse<HomeListMoviesResponse, String, RequestModel<Void>>> = GenericRequest()
    val responseListMoviesUpcoming: GenericRequest<GenericResponse<HomeListMoviesResponse, String, RequestModel<Void>>> = GenericRequest()
    val responseListMoviesTop: GenericRequest<GenericResponse<HomeListMoviesResponse, String, RequestModel<Void>>> = GenericRequest()
    val responseListMoviesNowPopular: GenericRequest<GenericResponse<HomeListMoviesResponse, String, RequestModel<Void>>> = GenericRequest()

    fun requestDataUpcoming(path: String, requestModel: RequestModel<Void>) {
        responseListMoviesPopular.postValue(GenericResponse(StatusRequestEnum.LOADING, requestData = requestModel))
        interactor.getListMoviesUpcoming( path, requestModel) {
            responseListMoviesUpcoming.postValue(
                it
            )
        }
    }
    fun requestDataPopular(path: String, requestModel: RequestModel<Void>) {
        responseListMoviesPopular.postValue(GenericResponse(StatusRequestEnum.LOADING, requestData = requestModel))
        interactor.getListMoviesPopulars( path, requestModel) {
            responseListMoviesPopular.postValue(
                it
            )
        }
    }
    fun requestDataTop(path: String, requestModel: RequestModel<Void>) {
        responseListMoviesPopular.postValue(GenericResponse(StatusRequestEnum.LOADING, requestData = requestModel))
        interactor.getListMoviesTop( path, requestModel) {
            responseListMoviesTop.postValue(
                it
            )
        }
    }
    fun requestDataNowPlaying(path: String, requestModel: RequestModel<Void>) {
        responseListMoviesPopular.postValue(GenericResponse(StatusRequestEnum.LOADING, requestData = requestModel))
        interactor.getListMoviesNowPlaying( path, requestModel) {
            responseListMoviesNowPopular.postValue(
                it
            )
        }
    }
}