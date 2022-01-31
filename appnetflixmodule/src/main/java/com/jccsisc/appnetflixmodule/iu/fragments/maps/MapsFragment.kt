package com.jccsisc.appnetflixmodule.iu.fragments.maps

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.jccsisc.appnetflixmodule.R
import com.jccsisc.appnetflixmodule.databinding.FragmentMapsBinding
import com.jccsisc.appnetflixmodule.iu.fragments.maps.model.UserLocation
import com.jccsisc.appnetflixmodule.iu.fragments.maps.service.MyService
import com.jccsisc.appnetflixmodule.utils.ConstantsObject.COLL_USER
import com.jccsisc.appnetflixmodule.utils.DialogObject
import com.jccsisc.appnetflixmodule.utils.getUserLocation
import com.jccsisc.appnetflixmodule.utils.showToast
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MapsFragment : Fragment(R.layout.fragment_maps), OnMapReadyCallback {

    lateinit var mBinding: FragmentMapsBinding
    lateinit var mapFragment: SupportMapFragment
    lateinit var map: GoogleMap
    private var userLocation = Location("")
    private lateinit var coordinates: LatLng

    val viewModel by viewModels<MapsViewModel>()

    companion object {
        const val REQUEST_CODE_LOCATION = 0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mBinding = FragmentMapsBinding.bind(view)
        super.onViewCreated(mBinding.root, savedInstanceState)
        initElements()
        initObserversMaps()
    }

    private fun createFragment() {
        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun crateMarker() {
        val marker = MarkerOptions()
            .position(coordinates)
            .title(getCompleteAddressString(coordinates.latitude, coordinates.longitude))
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.location))
        map.addMarker(marker)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 20.0f))
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        crateMarker()
    }

    private fun isLocationPermissionGranted() = ContextCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    fun requestLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isLocationPermissionGranted()) {
                getUserLocation {
                    if (it == null) {
                        //No tienes habilitado el permiso de localizacion
                        return@getUserLocation
                    }

                    userLocation.latitude = it.latitude
                    userLocation.longitude = it.longitude
                    coordinates = LatLng(userLocation.latitude, userLocation.longitude)
                    if (!::mapFragment.isInitialized) {
                        createFragment()
                    }
                }
            } else {
                val permissionArray = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
                requestPermissions(permissionArray, REQUEST_CODE_LOCATION)
            }
        } else {
            getUserLocation {
                userLocation.latitude = it?.latitude ?: 0.0
                userLocation.longitude = it?.longitude ?: 0.0
                coordinates = LatLng(userLocation.latitude, userLocation.longitude)
                if (!::mapFragment.isInitialized) {
                    createFragment()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_LOCATION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getUserLocation {
                        if (it == null) {
                            //No tienes habilitado el permiso de localizacion
                            return@getUserLocation
                        }

                        userLocation.latitude = it.latitude
                        userLocation.longitude = it.longitude
                        coordinates = LatLng(userLocation.latitude, userLocation.longitude)
                        if (!::mapFragment.isInitialized) {
                            createFragment()
                        }
                    }
                } else if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    showLocationPermissionDialog()
                } else {
                    showToast(getString(R.string.you_need_to_give_location), Toast.LENGTH_LONG)
                    findNavController().popBackStack()
                }
            }
            else -> {

            }
        }
    }


    private fun getCompleteAddressString(latitude: Double, longitude: Double): String {
        var strAdd = ""
        activity?.let { activity ->
            val geocoder = Geocoder(activity, Locale.getDefault())
            val addresses: java.util.ArrayList<Address>
            try {
                addresses =
                    geocoder.getFromLocation(latitude, longitude, 1) as java.util.ArrayList<Address>
                val address: String = addresses[0].getAddressLine(0)

                strAdd = address
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return strAdd
    }

    private fun showLocationPermissionDialog() {
        activity?.let { activity ->
            DialogObject.showDialog(
                activity,
                getString(R.string.you_need_location_permission),
                isCancelable = false,
                isOk = false,
                textDialog = getString(R.string.acecept_location_permission),
                btnAcept = {
                    requestPermissions(
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        REQUEST_CODE_LOCATION
                    )
                },
                btnNegate = {
                    findNavController().popBackStack()
                },
                showBottomsPermissions = true
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(MyService::class.java.simpleName, "OndestroyFragment...")
    }
}