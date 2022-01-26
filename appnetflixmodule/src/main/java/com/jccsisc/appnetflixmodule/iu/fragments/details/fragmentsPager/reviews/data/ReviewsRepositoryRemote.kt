package com.jccsisc.appnetflixmodule.iu.fragments.details.fragmentsPager.reviews.data

import androidx.lifecycle.Observer
import com.jccsisc.appnetflixmodule.BuildConfig
import com.jccsisc.appnetflixmodule.common.core.InitServices
import com.jccsisc.appnetflixmodule.common.core.ValidResponse
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.moviesmodule.common.MMEnumsURL
import com.jccsisc.appnetflixmodule.iu.fragments.details.fragmentsPager.reviews.model.ReviewsResponseModel
import retrofit2.Call

class ReviewsRepositoryRemote: ReviewsSource {

    override fun getListReviews(
        idMovie: String,
        requestModel: RequestModel<Void>,
        observer: Observer<GenericResponse<ReviewsResponseModel, String, RequestModel<Void>>>
    ) {
        val initService: InitServices<Void, Call<ReviewsResponseModel>> = InitServices(BuildConfig.BASE_URL)

        ValidResponse(observer, requestModel, ReviewsResponseModel::class).validationMethod(
            initService.getExecuteServiceMovies(
                idMovie.plus("/reviews"),
                MMEnumsURL.API_KEY.url
            )
        )
    }
}