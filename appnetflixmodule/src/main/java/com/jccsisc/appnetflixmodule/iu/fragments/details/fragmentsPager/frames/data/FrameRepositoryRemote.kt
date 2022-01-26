package com.jccsisc.appnetflixmodule.iu.fragments.details.fragmentsPager.frames.data

import androidx.lifecycle.Observer
import com.jccsisc.appnetflixmodule.BuildConfig
import com.jccsisc.appnetflixmodule.common.core.InitServices
import com.jccsisc.appnetflixmodule.common.core.ValidResponse
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.moviesmodule.common.MMEnumsURL
import com.jccsisc.appnetflixmodule.iu.fragments.details.fragmentsPager.frames.model.FrameImageResponse
import retrofit2.Call

class FrameRepositoryRemote: FrameSource {

    override fun getListImages(idMovie: String, requestModel: RequestModel<Void>, observer: Observer<GenericResponse<FrameImageResponse, String, RequestModel<Void>>>) {

        val initService: InitServices<Void, Call<FrameImageResponse>> = InitServices(BuildConfig.BASE_URL)

        ValidResponse(observer, requestModel, FrameImageResponse::class).validationMethod(
                initService.getExecuteServiceMovies(
                        idMovie.plus("/images"),
                        MMEnumsURL.API_KEY.url
                )
        )
    }
}