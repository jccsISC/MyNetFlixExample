package com.jccsisc.appnetflixmodule.iu.fragments.gallery.data

import android.util.Log
import androidx.lifecycle.Observer
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.appnetflixmodule.common.core.status.StatusRequestEnum
import com.jccsisc.appnetflixmodule.iu.fragments.maps.model.UserLocation
import com.jccsisc.appnetflixmodule.utils.ConstantsObject

class GalleryRepositoryRemote : UsesCasesGallery {

    override fun getAllLocation(
        observer: Observer<GenericResponse<UserLocation, String, RequestModel<Void>>>
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
                                    successData = UserLocation(
                                        latitud = locationUsr.latitud,
                                        longitud = locationUsr.longitud,
                                        image = locationUsr.image
                                    )
                                )
                            )
                        }
                        else -> {
                            Log.d("Error", "")
                        }
                    }
                }
                /*observer.onChanged(
                    GenericResponse(
                        StatusRequestEnum.FAILURE,
                        errorData = "No hay ninguna imagen cargada"
                    )
                )*/
            }
        }
    }

    override fun uploatLocationWithImage(
        requestModel: RequestModel<UserLocation>,
        observer: Observer<GenericResponse<String, String, RequestModel<UserLocation>>>
    ) {
        with(FirebaseFirestore.getInstance()) {
            collection(ConstantsObject.COLL_USER)
                .add(requestModel.requestBody!!)
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