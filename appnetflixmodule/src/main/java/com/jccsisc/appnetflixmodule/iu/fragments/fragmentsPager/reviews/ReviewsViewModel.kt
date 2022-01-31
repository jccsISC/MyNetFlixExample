package com.jccsisc.appnetflixmodule.iu.fragments.fragmentsPager.reviews

import androidx.lifecycle.ViewModel
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.appnetflixmodule.iu.fragments.fragmentsPager.reviews.data.ReviewsInteractor
import com.jccsisc.appnetflixmodule.iu.fragments.fragmentsPager.reviews.model.ReviewsResponseModel
import com.jccsisc.appnetflixmodule.utils.doRequest

class ReviewsViewModel: ViewModel() {
    private val interactor = ReviewsInteractor()
    val responseListReviews by lazy {
        GenericResponse<ReviewsResponseModel, String, RequestModel<Void>>()
    }


    fun requestData(idMovie: String, requestModel: RequestModel<Void>) {
        responseListReviews.doRequest {
            interactor.getListReviews(idMovie, requestModel) {
                responseListReviews.postValue(
                    it
                )
            }
        }
    }
}