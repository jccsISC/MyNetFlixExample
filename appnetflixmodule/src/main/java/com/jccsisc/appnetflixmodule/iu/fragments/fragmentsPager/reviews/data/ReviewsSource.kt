package com.jccsisc.appnetflixmodule.iu.fragments.fragmentsPager.reviews.data

import androidx.lifecycle.Observer
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.appnetflixmodule.iu.fragments.fragmentsPager.reviews.model.ReviewsResponseModel

interface ReviewsSource {
    fun getListReviews(
        idMovie: String,
        requestModel: RequestModel<Void>,
        observer: Observer<GenericResponse<ReviewsResponseModel, String, RequestModel<Void>>>
    )
}