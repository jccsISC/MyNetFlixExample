package com.jccsisc.appnetflixmodule.iu.fragments.gallery.data

import androidx.lifecycle.Observer
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.appnetflixmodule.iu.fragments.maps.model.UserLocation

/****
 * Project: MyNetflixApp
 * From: com.jccsisc.appnetflixmodule.iu.fragments.maps.data
 * Created by Julio Cesar Camacho Silva on 30/01/2022 at 20:09
 * More info: https://www.facebook.com/juliocesar.camachosilva/
 * All rights reserved 2022.
 ***/
interface UsesCasesGallery {

    fun getAllLocation(
        observer: Observer<GenericResponse<UserLocation, String, RequestModel<Void>>>)

    fun uploatLocationWithImage(
        requestModel: RequestModel<UserLocation>,
        observer: Observer<GenericResponse<String, String, RequestModel<UserLocation>>>
    )
}