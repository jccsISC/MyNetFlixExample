package com.jccsisc.appnetflixmodule.iu.fragments.gallery.data

import androidx.lifecycle.Observer
import com.google.android.gms.maps.model.LatLng
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.appnetflixmodule.iu.fragments.maps.model.UserLocation

class GalleryRepositoryLocal : UsesCasesGallery {

    override fun getAllLocation(
        observer: Observer<GenericResponse<UserLocation, String, RequestModel<Void>>>) {

    }

    override fun uploatLocationWithImage(
        requestModel: RequestModel<UserLocation>,
        observer: Observer<GenericResponse<String, String, RequestModel<UserLocation>>>
    ) {

    }
}