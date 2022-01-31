package com.jccsisc.appnetflixmodule.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng

/****
 * Project: MyNetflixApp
 * From: com.jccsisc.appnetflixmodule.utils
 * Created by Julio Cesar Camacho Silva on 30/01/2022 at 21:13
 * More info: https://www.facebook.com/juliocesar.camachosilva/
 * All rights reserved 2022.
 ***/

fun Fragment.getUserLocation(result: (location: Location?) -> Unit) {
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

    if (ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        result.invoke(null)
        return
    }
    fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
        if (location != null) {

            result.invoke(location)
        }
    }
}
