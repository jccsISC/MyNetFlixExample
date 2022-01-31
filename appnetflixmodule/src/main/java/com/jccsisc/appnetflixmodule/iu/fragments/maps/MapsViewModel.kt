package com.jccsisc.appnetflixmodule.iu.fragments.maps
/****
 * Project: MyNetflixApp
 * From: com.jccsisc.appnetflixmodule.iu.fragments.maps
 * Created by Julio Cesar Camacho Silva on 29/01/2022 at 3:00
 * More info: https://www.facebook.com/juliocesar.camachosilva/
 * All rights reserved 2022.
 ***/
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.appnetflixmodule.iu.fragments.maps.data.MapsInteractor
import com.jccsisc.appnetflixmodule.iu.fragments.maps.model.UserLocation
import com.jccsisc.appnetflixmodule.utils.doRequest

class MapsViewModel : ViewModel() {
    var statusService = MutableLiveData(false)

    private val interactor = MapsInteractor()

    private val responseListLocation by lazy {
        GenericResponse<LatLng, String, RequestModel<Void>>()
    }

    private val responseUploatLocation by lazy {
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
            interactor.uploatLocation(requestInfo.requestData ?: requestModel) {
                responseUploatLocation.postValue(
                    it
                )
            }
        }
    }
}




