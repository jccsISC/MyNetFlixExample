package com.jccsisc.appnetflixmodule.iu.fragments.maps.data

import androidx.lifecycle.Observer
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.appnetflixmodule.iu.fragments.maps.model.UserLocation

class MapsRepositoryLocal : UsesCases {

    override fun getAllLocation(
        observer: Observer<GenericResponse<LatLng, String, RequestModel<Void>>>) {

    }

    override fun uploatLocation(
        requestModel: RequestModel<UserLocation>,
        observer: Observer<GenericResponse<String, String, RequestModel<UserLocation>>>
    ) {

    }
}