package com.jccsisc.appnetflixmodule.iu.fragments.information.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/****
 * Project: MyNetflixApp
 * From: com.jccsisc.appnetflixmodule.iu.fragments.information.model
 * Created by Julio Cesar Camacho Silva on 26/01/2022 at 13:08
 * More info: https://www.facebook.com/juliocesar.camachosilva/
 * All rights reserved 2022.
 ***/
@Parcelize
data class InformationModel(
    val image: String = "",
    val title: String = "",
    val rating: String = "",
    val language: String = "",
    val description: String = ""
) : Parcelable
