package com.jccsisc.appnetflixmodule.iu.fragments.maps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.jccsisc.appnetflixmodule.R
import com.jccsisc.appnetflixmodule.databinding.FragmentMapsBinding

class MapsFragment : Fragment(R.layout.fragment_maps), OnMapReadyCallback {

    lateinit var mBinding: FragmentMapsBinding
    lateinit var map: GoogleMap
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mBinding = FragmentMapsBinding.bind(view)
        super.onViewCreated(mBinding.root, savedInstanceState)
        mBinding.apply {
            createFragment()
        }
    }

    private fun createFragment() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        createMarker()
    }

    private fun createMarker() {
        val coordinates = LatLng(28.043893, -16.539329)
        val marker = MarkerOptions().position(coordinates).title("Playa")
        map.addMarker(marker)
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 17.3f), 3000, null)
    }
}