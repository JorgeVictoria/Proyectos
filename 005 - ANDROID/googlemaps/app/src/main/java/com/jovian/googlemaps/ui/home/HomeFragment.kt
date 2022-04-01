package com.jovian.googlemaps.ui.home

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup
import androidx.navigation.ui.AppBarConfiguration

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.jovian.googlemaps.R

class HomeFragment : Fragment() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    @SuppressLint("MissingPermission")
    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */

        //ponemos la localizacion inicial en Torrent
        val torrent = LatLng(39.4372481, -0.467581)

        //ponemos la marca
        val mmarker = googleMap.addMarker(MarkerOptions().position(torrent).title("Marker in Torrent"))

        //quitamos la marca
        if (mmarker != null) {
            mmarker.remove()
        }

        //ponemos la camera con un zoom de 12
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(torrent, 12f))

        //deshabilitamos el control de zoom
        googleMap.getUiSettings().setZoomControlsEnabled(false)

        //prohibimos la rotacion con los dedos
        googleMap.getUiSettings().setRotateGesturesEnabled(false)

        //desactivamos nuestra localizacionIb
        googleMap.setMyLocationEnabled(false)

        //aplicamos el estilo
        googleMap.setMapStyle (MapStyleOptions.loadRawResourceStyle (this.requireContext(), R.raw.style_json))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}