package com.jccsisc.appnetflixmodule.iu.fragments.fragmentsPager.reviews.data

import androidx.lifecycle.Observer
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.appnetflixmodule.iu.fragments.fragmentsPager.reviews.model.ReviewsResponseModel

class ReviewsInteractor: ReviewsSource {

    private val localRepository by lazy { ReviewsRepositoryLocal() }
    private val remoteRepository by lazy { ReviewsRepositoryRemote() }

    var defaultRemote = true

    override fun getListReviews(
        idMovie: String,
        requestModel: RequestModel<Void>,
        observer: Observer<GenericResponse<ReviewsResponseModel, String, RequestModel<Void>>>
    ) {
        if (defaultRemote) {
            remoteRepository.getListReviews(idMovie, requestModel, observer)
        } else {
            localRepository.getListReviews(idMovie, requestModel, observer)
        }
    }
}