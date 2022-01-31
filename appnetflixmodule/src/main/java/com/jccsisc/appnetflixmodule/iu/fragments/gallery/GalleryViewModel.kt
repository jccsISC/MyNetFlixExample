package com.jccsisc.appnetflixmodule.iu.fragments.gallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.appnetflixmodule.iu.fragments.gallery.data.GalleryInteractor
import com.jccsisc.appnetflixmodule.iu.fragments.maps.data.MapsInteractor
import com.jccsisc.appnetflixmodule.iu.fragments.maps.model.UserLocation
import com.jccsisc.appnetflixmodule.utils.doRequest

/****
 * Project: MyNetflixApp
 * From: com.jccsisc.appnetflixmodule.iu.fragments.gallery
 * Created by Julio Cesar Camacho Silva on 26/01/2022 at 22:33
 * More info: https://www.facebook.com/juliocesar.camachosilva/
 * All rights reserved 2022.
 ***/
class GalleryViewModel: ViewModel() {

    private val interactor = GalleryInteractor()

    val responseListLocation by lazy {
        GenericResponse<UserLocation, String, RequestModel<Void>>()
    }

    val responseUploatLocation by lazy {
        GenericResponse<String, String, RequestModel<UserLocation>>()
    }

    fun requestDataGetLocations() {
        responseListLocation.doRequest {
            interactor.getAllLocation {result ->
                responseListLocation.postValue(
                    result
                )
            }
        }
    }

    fun requestUploatLocation(requestModel: RequestModel<UserLocation>) {
        responseUploatLocation.doRequest(requestModel) { requestInfo ->
            interactor.uploatLocationWithImage(requestInfo.requestData ?: requestModel) {
                responseUploatLocation.postValue(
                    it
                )
            }
        }
    }
}