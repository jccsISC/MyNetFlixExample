package com.jccsisc.appnetflixmodule.iu.fragments.maps.data

import androidx.lifecycle.Observer
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.appnetflixmodule.common.core.status.StatusRequestEnum
import com.jccsisc.appnetflixmodule.iu.fragments.maps.model.UserLocation
import com.jccsisc.appnetflixmodule.utils.ConstantsObject

class MapsRepositoryRemote : UsesCases {

    override fun getAllLocation(
        observer: Observer<GenericResponse<LatLng, String, RequestModel<Void>>>
    ) {

        with(FirebaseFirestore.getInstance()) {

            val locationsUserRef = collection(ConstantsObject.COLL_USER)

            locationsUserRef.addSnapshotListener { snapshots, error ->
                if (error != null) {
                    observer.onChanged(
                        GenericResponse(
                            StatusRequestEnum.FAILURE
                        )
                    )
                }

                for (snapshot in snapshots!!.documentChanges) {
                    val locationUsr = snapshot.document.toObject(UserLocation::class.java)
                    locationUsr.idUser = snapshot.document.id
                    when (snapshot.type) {
                        DocumentChange.Type.ADDED -> {
                            observer.onChanged(
                                GenericResponse(
                                    StatusRequestEnum.SUCCESS,
                                    successData = LatLng(
                                        locationUsr.latitud,
                                        locationUsr.longitud
                                    )
                                )
                            )
                        }
                        else -> {
                        }
                    }
                }
            }
        }
    }

    override fun uploatLocation(
        requestModel: RequestModel<UserLocation>,
        observer: Observer<GenericResponse<String, String, RequestModel<UserLocation>>>
    ) {
        with(FirebaseFirestore.getInstance()) {
            collection(ConstantsObject.COLL_USER)
                .add(requestModel)
                .addOnSuccessListener {
                    observer.onChanged(
                        GenericResponse(
                            StatusRequestEnum.SUCCESS,
                            "Se subió correctamente"
                        )
                    )
                }
                .addOnFailureListener {
                    observer.onChanged(
                        GenericResponse(
                            StatusRequestEnum.FAILURE,
                            "Error"
                        )
                    )
                }
        }
    }
}