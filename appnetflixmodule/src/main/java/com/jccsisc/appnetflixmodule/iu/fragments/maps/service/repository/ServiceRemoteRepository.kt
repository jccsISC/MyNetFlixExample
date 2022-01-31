package com.jccsisc.appnetflixmodule.iu.fragments.maps.service.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.jccsisc.appnetflixmodule.iu.fragments.maps.model.UserLocation
import com.jccsisc.appnetflixmodule.iu.fragments.maps.service.SERVICE_TAG
import com.jccsisc.appnetflixmodule.utils.ConstantsObject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/****
 * Project: MyNetflixApp
 * From: com.jccsisc.appnetflixmodule.iu.fragments.maps.service.repository
 * Created by Julio Cesar Camacho Silva on 30/01/2022 at 22:28
 * More info: https://www.facebook.com/juliocesar.camachosilva/
 * All rights reserved 2022.
 ***/
class ServiceRemoteRepository {

    fun saveLocation(locationUser: UserLocation, response: (result: Boolean) -> Unit) {

        try {
            with(FirebaseFirestore.getInstance()) {
                collection(ConstantsObject.COLL_USER).document()
                    .set(locationUser)
                    .addOnSuccessListener {
                        Log.d(SERVICE_TAG, "OK")
                        response(true)
                    }
                    .addOnFailureListener {
                        Log.d(SERVICE_TAG, "Failure")
                        response(false)
                    }.addOnCanceledListener {
                        Log.d(SERVICE_TAG, "Failure")
                        response(false)
                    }
            }
        } catch (e: Exception) {
            response(false)
        }
    }
}