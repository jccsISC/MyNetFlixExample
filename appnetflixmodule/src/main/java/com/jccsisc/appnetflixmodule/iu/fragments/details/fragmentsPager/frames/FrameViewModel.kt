package com.jccsisc.appnetflixmodule.iu.fragments.details.fragmentsPager.frames

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jccsisc.appnetflixmodule.common.core.request.GenericRequest
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.appnetflixmodule.common.core.status.StatusRequestEnum
import com.jccsisc.appnetflixmodule.iu.fragments.details.fragmentsPager.frames.data.FrameInteractor
import com.jccsisc.appnetflixmodule.iu.fragments.details.fragmentsPager.frames.model.FrameImageResponse

class FrameViewModel: ViewModel() {
    private val interactor = FrameInteractor()
    val responseListImages: GenericRequest<GenericResponse<FrameImageResponse, String, RequestModel<Void>>> = GenericRequest()
    val _listImageLiveData = MutableLiveData<FrameImageResponse>()
    val listImageLiveData: LiveData<FrameImageResponse>
        get() = _listImageLiveData

    fun requestData(idMovie: String, requestModel: RequestModel<Void>) {
        responseListImages.postValue(GenericResponse(StatusRequestEnum.LOADING, requestData = requestModel))
        interactor.getListImages(idMovie, requestModel) {
            responseListImages.postValue(
                    it
            )
        }
    }
}