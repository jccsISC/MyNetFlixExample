package com.jccsisc.appnetflixmodule.iu.fragments.details.fragmentsPager.reviews

import androidx.lifecycle.ViewModel
import com.jccsisc.appnetflixmodule.common.core.request.GenericRequest
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.appnetflixmodule.common.core.status.StatusRequestEnum
import com.jccsisc.appnetflixmodule.iu.fragments.details.fragmentsPager.reviews.data.ReviewsInteractor
import com.jccsisc.appnetflixmodule.iu.fragments.details.fragmentsPager.reviews.model.ReviewsResponseModel

class ReviewsViewModel: ViewModel() {
    private val interactor = ReviewsInteractor()
    val responseListReviews: GenericRequest<GenericResponse<ReviewsResponseModel, String, RequestModel<Void>>> = GenericRequest()


    fun requestData(idMovie: String, requestModel: RequestModel<Void>) {
        responseListReviews.postValue(GenericResponse(StatusRequestEnum.LOADING, requestData = requestModel))
        interactor.getListReviews(idMovie, requestModel) {
            responseListReviews.postValue(
                    it
            )
        }
    }
}