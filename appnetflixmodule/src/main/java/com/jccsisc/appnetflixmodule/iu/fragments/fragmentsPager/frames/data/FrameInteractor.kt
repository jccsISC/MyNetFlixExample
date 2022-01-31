package com.jccsisc.appnetflixmodule.iu.fragments.fragmentsPager.frames.data

import androidx.lifecycle.Observer
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.appnetflixmodule.iu.fragments.fragmentsPager.frames.model.FrameImageResponse

class FrameInteractor: FrameSource {

    private val localRepository by lazy { FrameRepositoryLocal() }
    private val remoteRepository by lazy { FrameRepositoryRemote() }

    var defaultRemote = true

    override fun getListImages(idMovie: String, requestModel: RequestModel<Void>, observer: Observer<GenericResponse<FrameImageResponse, String, RequestModel<Void>>>) {
        if (defaultRemote) {
            remoteRepository.getListImages(idMovie, requestModel, observer)
        } else {
            localRepository.getListImages(idMovie, requestModel, observer)
        }
    }
}