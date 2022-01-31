package com.jccsisc.appnetflixmodule.iu.fragments.fragmentsPager.frames.data

import androidx.lifecycle.Observer
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.appnetflixmodule.iu.fragments.fragmentsPager.frames.model.FrameImageResponse

class FrameRepositoryLocal: FrameSource {

    override fun getListImages(idMovie: String, requestModel: RequestModel<Void>, observer: Observer<GenericResponse<FrameImageResponse, String, RequestModel<Void>>>) {

    }
}