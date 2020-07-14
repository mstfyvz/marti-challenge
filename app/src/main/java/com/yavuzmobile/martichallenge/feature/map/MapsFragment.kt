package com.yavuzmobile.martichallenge.feature.map

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.yavuzmobile.martichallenge.R
import com.yavuzmobile.martichallenge.base.BaseFragment


class MapsFragment : BaseFragment(R.layout.fragment_maps) {

    private val args: MapsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?

        args.candidates.let {
            mapFragment?.getMapAsync { googleMap ->
                val latLng = LatLng(it?.geometry?.location?.lat!!, it.geometry.location.lat)
                googleMap.addMarker(MarkerOptions().position(latLng).title(it.formatted_address))
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12f))
                googleMap.uiSettings.isZoomControlsEnabled = true

                googleMap.setOnMarkerClickListener { marker ->
                    findNavController().navigate(MapsFragmentDirections.actionMapsFragmentToDetailFragment(it))
                    return@setOnMarkerClickListener  true
                }
            }
        }
    }
}