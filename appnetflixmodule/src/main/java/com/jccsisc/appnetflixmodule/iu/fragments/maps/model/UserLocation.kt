package com.jccsisc.appnetflixmodule.iu.fragments.maps.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.Exclude

/****
 * Project: MyNetflixApp
 * From: com.jccsisc.appnetflixmodule.iu.fragments.maps.model
 * Created by Julio Cesar Camacho Silva on 27/01/2022 at 15:54
 * More info: https://www.facebook.com/juliocesar.camachosilva/
 * All rights reserved 2022.
 ***/
data class UserLocation(
    @get:Exclude var idUser: String = "",
    val latitud: Double = 0.0,
    val longitud: Double = 0.0,
    val date: Timestamp = Timestamp.now(),
    val image: String = ""
)