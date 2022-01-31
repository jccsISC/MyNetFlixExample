package com.jccsisc.appnetflixmodule.iu.fragments.maps.data

import androidx.lifecycle.Observer
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.appnetflixmodule.iu.fragments.maps.model.UserLocation

class MapsInteractor : UsesCases {

    private val localRepository by lazy { MapsRepositoryLocal() }
    private val remoteRepository by lazy { MapsRepositoryRemote() }

    var defaultRemote = true

    override fun getAllLocation(
        observer: Observer<GenericResponse<LatLng, String, RequestModel<Void>>>) {
        if (defaultRemote) {
            remoteRepository.getAllLocation(observer)
        } else {
            localRepository.getAllLocation(observer)
        }
    }

    override fun uploatLocation(
        requestModel: RequestModel<UserLocation>,
        observer: Observer<GenericResponse<String, String, RequestModel<UserLocation>>>
    ) {
        if (defaultRemote) {
            remoteRepository.uploatLocation(requestModel, observer)
        } else {
            localRepository.uploatLocation(requestModel, observer)
        }
    }
}