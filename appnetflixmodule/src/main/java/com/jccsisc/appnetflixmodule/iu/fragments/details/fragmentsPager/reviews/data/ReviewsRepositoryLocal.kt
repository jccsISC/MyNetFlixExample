package com.jccsisc.appnetflixmodule.iu.fragments.details.fragmentsPager.reviews.data

import androidx.lifecycle.Observer
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.appnetflixmodule.iu.fragments.details.fragmentsPager.reviews.model.ReviewsResponseModel

class ReviewsRepositoryLocal: ReviewsSource {

    override fun getListReviews(
        idMovie: String,
        requestModel: RequestModel<Void>,
        observer: Observer<GenericResponse<ReviewsResponseModel, String, RequestModel<Void>>>
    ) {

    }
}