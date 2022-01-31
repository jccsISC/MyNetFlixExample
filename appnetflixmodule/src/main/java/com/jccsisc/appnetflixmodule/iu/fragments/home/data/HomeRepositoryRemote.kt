package com.jccsisc.appnetflixmodule.iu.fragments.home.data

import androidx.lifecycle.Observer
import com.jccsisc.appnetflixmodule.BuildConfig
import com.jccsisc.appnetflixmodule.common.core.InitServices
import com.jccsisc.appnetflixmodule.common.core.ValidResponse
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.appnetflixmodule.iu.fragments.home.model.HomeListMoviesResponse
import com.jccsisc.appnetflixmodule.common.MMEnumsURL
import retrofit2.Call

class HomeRepositoryRemote : UsesCases {

    override fun getListMoviesPopulars(
        path: String,
        requestModel: RequestModel<Void>,
        observer: Observer<GenericResponse<HomeListMoviesResponse, String, RequestModel<Void>>>
    ) {
        val initService: InitServices<Void, Call<HomeListMoviesResponse>> =
            InitServices(BuildConfig.BASE_URL)

        ValidResponse(observer, requestModel, HomeListMoviesResponse::class).validationMethod(
            initService.getExecuteServiceMovies(
                path,
                MMEnumsURL.API_KEY.url
            )
        )
    }

    override fun getListMoviesUpcoming(
        path: String,
        requestModel: RequestModel<Void>,
        observer: Observer<GenericResponse<HomeListMoviesResponse, String, RequestModel<Void>>>
    ) {
        val initService: InitServices<Void, Call<HomeListMoviesResponse>> =
            InitServices(BuildConfig.BASE_URL)

        ValidResponse(observer, requestModel, HomeListMoviesResponse::class).validationMethod(
            initService.getExecuteServiceMovies(
                path,
                MMEnumsURL.API_KEY.url
            )
        )
    }

    override fun getListMoviesTop(
        path: String,
        requestModel: RequestModel<Void>,
        observer: Observer<GenericResponse<HomeListMoviesResponse, String, RequestModel<Void>>>
    ) {
        val initService: InitServices<Void, Call<HomeListMoviesResponse>> =
            InitServices(BuildConfig.BASE_URL)

        ValidResponse(observer, requestModel, HomeListMoviesResponse::class).validationMethod(
            initService.getExecuteServiceMovies(
                path,
                MMEnumsURL.API_KEY.url
            )
        )
    }

    override fun getListMoviesNowPlaying(
        path: String,
        requestModel: RequestModel<Void>,
        observer: Observer<GenericResponse<HomeListMoviesResponse, String, RequestModel<Void>>>
    ) {
        val initService: InitServices<Void, Call<HomeListMoviesResponse>> =
            InitServices(BuildConfig.BASE_URL)

        ValidResponse(observer, requestModel, HomeListMoviesResponse::class).validationMethod(
            initService.getExecuteServiceMovies(
                path,
                MMEnumsURL.API_KEY.url
            )
        )
    }
}