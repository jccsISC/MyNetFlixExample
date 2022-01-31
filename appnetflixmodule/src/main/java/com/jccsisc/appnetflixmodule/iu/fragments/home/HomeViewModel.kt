package com.jccsisc.appnetflixmodule.iu.fragments.home

import androidx.lifecycle.ViewModel
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.appnetflixmodule.iu.fragments.home.data.HomeInteractor
import com.jccsisc.appnetflixmodule.iu.fragments.home.model.HomeListMoviesResponse
import com.jccsisc.appnetflixmodule.utils.doRequest

class HomeViewModel : ViewModel() {

    private val interactor = HomeInteractor()
    val responseListMoviesUpcoming by lazy {
        GenericResponse<HomeListMoviesResponse, String, RequestModel<Void>>()
    }
    val responseListMoviesPopular by lazy {
        GenericResponse<HomeListMoviesResponse, String, RequestModel<Void>>()
    }
    val responseListMoviesTop by lazy {
        GenericResponse<HomeListMoviesResponse, String, RequestModel<Void>>()
    }
    val responseListMoviesNowPopular by lazy {
        GenericResponse<HomeListMoviesResponse, String, RequestModel<Void>>()
    }

    fun requestDataUpcoming(path: String, requestModel: RequestModel<Void>) {
        responseListMoviesUpcoming.doRequest {
            interactor.getListMoviesUpcoming(path, requestModel) {
                responseListMoviesUpcoming.postValue(
                    it
                )
            }
        }
    }

    fun requestDataPopular(path: String, requestModel: RequestModel<Void>) {
        responseListMoviesPopular.doRequest {
            interactor.getListMoviesPopulars(path, requestModel) {
                responseListMoviesPopular.postValue(
                    it
                )
            }
        }
    }

    fun requestDataTop(path: String, requestModel: RequestModel<Void>) {
        responseListMoviesTop.doRequest {
            interactor.getListMoviesTop(path, requestModel) {
                responseListMoviesTop.postValue(
                    it
                )
            }
        }
    }

    fun requestDataNowPlaying(path: String, requestModel: RequestModel<Void>) {
        responseListMoviesNowPopular.doRequest {
            interactor.getListMoviesNowPlaying(path, requestModel) {
                responseListMoviesNowPopular.postValue(
                    it
                )
            }
        }
    }
}