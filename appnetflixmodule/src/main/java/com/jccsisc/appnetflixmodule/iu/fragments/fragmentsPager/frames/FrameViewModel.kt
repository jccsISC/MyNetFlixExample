package com.jccsisc.appnetflixmodule.iu.fragments.fragmentsPager.frames

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.appnetflixmodule.iu.fragments.fragmentsPager.frames.data.FrameInteractor
import com.jccsisc.appnetflixmodule.iu.fragments.fragmentsPager.frames.model.FrameImageResponse
import com.jccsisc.appnetflixmodule.utils.doRequest

class FrameViewModel : ViewModel() {
    private val interactor = FrameInteractor()
    val responseListImages by lazy {
        GenericResponse<FrameImageResponse, String, RequestModel<Void>>()
    }
    val _listImageLiveData = MutableLiveData<FrameImageResponse>()
    val listImageLiveData: LiveData<FrameImageResponse>
        get() = _listImageLiveData

    fun requestData(idMovie: String, requestModel: RequestModel<Void>) {
        responseListImages.doRequest {
            interactor.getListImages(idMovie, requestModel) {
                responseListImages.postValue(
                    it
                )
            }
        }
    }
}