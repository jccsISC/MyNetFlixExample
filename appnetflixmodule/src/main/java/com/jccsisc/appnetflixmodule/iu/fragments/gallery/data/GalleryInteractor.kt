package com.jccsisc.appnetflixmodule.iu.fragments.gallery.data

import androidx.lifecycle.Observer
import com.google.android.gms.maps.model.LatLng
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.appnetflixmodule.iu.fragments.maps.model.UserLocation

class GalleryInteractor : UsesCasesGallery {

    private val localRepository by lazy { GalleryRepositoryLocal() }
    private val remoteRepository by lazy { GalleryRepositoryRemote() }

    var defaultRemote = true

    override fun getAllLocation(
        observer: Observer<GenericResponse<UserLocation, String, RequestModel<Void>>>) {
        if (defaultRemote) {
            remoteRepository.getAllLocation(observer)
        } else {
            localRepository.getAllLocation(observer)
        }
    }

    override fun uploatLocationWithImage(
        requestModel: RequestModel<UserLocation>,
        observer: Observer<GenericResponse<String, String, RequestModel<UserLocation>>>
    ) {
        if (defaultRemote) {
            remoteRepository.uploatLocationWithImage(requestModel, observer)
        } else {
            localRepository.uploatLocationWithImage(requestModel, observer)
        }
    }
}