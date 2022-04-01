package com.rucech.prueba2

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.navigation.NavigationView
import com.rucech.prueba2.databinding.ActivityMainBinding

const val REQUEST_PERSMISSION_CODE = 1000


open class MainActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mMap: GoogleMap
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private var mLatitude = 0.0
    private var mLongitude = 0.0
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private var eliminar : Boolean = false

    //Here we have the 3 lists where we store the Markers of different types, we could have put everything in one to optimize it but I preferred the organization as it is a simple app
    private var lista: MutableList<Marker> = mutableListOf()
    private var listaRes: MutableList<Marker> = mutableListOf()
    private var listaApo: MutableList<Marker> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        //We request permissions to the GPS
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        locationRequest = LocationRequest.create()
        locationRequest!!.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        locationRequest!!.interval = 16 // 16seconds
        locationRequest!!.fastestInterval = 8 // 8seconds
        locationRequest.smallestDisplacement = 0f





        //We indicate that the toolbar that we will use is the one that we have created in the layout
        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout //variable drawerLayout
        val navView: NavigationView = binding.navView //variable NavigationView


        //This listener controls when we click on the different items of the drawer menu item
        navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            if (item.itemId == R.id.nav_outapp) {
                finish() //close app
            } else if (item.itemId == R.id.nav_closedrawer) {
                drawerLayout.closeDrawers();//close drawer
            } else if (item.itemId == R.id.ocultar) {
                removeLocations()//It will not create more markers of my location
                //remove all markers from my location
                for (i in lista) {
                    i.remove()

                }
                drawerLayout.closeDrawers();//It shouldn't be here but it's more aesthetic


            } else if (item.itemId == R.id.verRestaurantes) {
                seeRestaurants(drawerLayout)//This method create the markers of the restaurants
            } else if (item.itemId == R.id.verApuestas) {
                seeApostasy(drawerLayout)//This method create the markers of the bets houses
            } else if (item.itemId == R.id.ocultarto) {

                    seeOcultar(drawerLayout)



            }else if (item.itemId == R.id.nav_home) {
                eliminar=true

                //We request location updates
                locationCallback = object : LocationCallback() {

                    override fun onLocationResult(locationResult: LocationResult) {
                        super.onLocationResult(locationResult)
                        for (location in locationResult.locations) {
                            if (location != null) {
                                mLatitude = location.latitude //latitude
                                mLongitude = location.longitude//longitude
                                val miUbicacion = LatLng(location.latitude, location.longitude)//create ubication
                                var xd = mMap.addMarker(
                                    MarkerOptions().snippet("Toca aquí para más info")
                                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.mifoto))
                                        .position(miUbicacion).title("Ubicacion actual")
                                )//Marker with snippet,tittle and my face
                                xd.tag = "Yo"
                                lista.add(xd) //add marker to a MutableList for after we can remove the markers


                                //Listener of the snippet in marker


                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(miUbicacion, 7f))//Let's go to the location of our GPS

                            }
                        }
                    }
                }
                requestLocations()

            }

            false
        })


    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap//create map

        //add black style
        mMap.setMapStyle(
            MapStyleOptions.loadRawResourceStyle(
                this,
                R.raw.mapa
            )
        )

        val miUbicacion = LatLng(39.4544794,0.5048153 ) //ubication of restaurant

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(miUbicacion, 7f))


        //Limit options to users
        mMap.uiSettings.isMapToolbarEnabled = false
        mMap.uiSettings.isRotateGesturesEnabled = false
        mMap.uiSettings.isCompassEnabled = false

        //we activate our location
        mMap.uiSettings.isMyLocationButtonEnabled = true

        val locationManager =
            this@MainActivity.getSystemService(LOCATION_SERVICE) as LocationManager

        val locationListener: LocationListener = object : LocationListener {
            override fun onLocationChanged(location: android.location.Location) {

            }

            //Methods we do not use
            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }
        //permissions...
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        locationManager.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            0,
            0f,
            locationListener
        )

        mMap.setOnInfoWindowClickListener(object :
            GoogleMap.OnInfoWindowClickListener {
            override fun onInfoWindowClick(marker: Marker) {
                //evaluates by the tag if to do x or y
                if (marker.tag?.equals("Yo") == true) {

                    //Dialog with icon,tittle,message,Buton and position
                    val dialog = AlertDialog.Builder(this@MainActivity)
                        .setTitle("myMapas").setIcon(R.mipmap.ic_launcher2)
                        .setMessage("Su ubicación actual es: \n\n " + "Lat: " + marker.position.latitude + " Lon: " + marker.position.longitude)
                        .setPositiveButton("Ok") { view, _ ->
                            view.dismiss()
                        }
                        .setCancelable(false) //not need canceled button
                        .create()

                    dialog.show()
                } else if (marker.tag?.equals("apostasy") == true) {


                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:" + marker.snippet)
                    startActivity(intent)//Go to the calling application with the number saved in the snippet

                } else if (marker.tag?.equals("restaurant") == true) {

                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(marker.snippet)
                    startActivity(intent)//Go to the website of the restaurant saved in the snippet

                }


            }
        })

    }
    //Method that we are obliged to implement even though we use it elsewhere
    override fun onInfoWindowClick(marker: Marker?) {

    }

    //more permissions...
    private fun requestLocations() {
        if (ActivityCompat.checkSelfPermission(
                this@MainActivity,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this@MainActivity,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED


        ) {
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                REQUEST_PERSMISSION_CODE
            )
        } else {

            mFusedLocationClient!!.requestLocationUpdates(
                locationRequest!!,
                locationCallback!!, Looper.getMainLooper()
            )
        }

    }

    //this method ensures that no more markers are created in our location
    private fun removeLocations() {
        if(eliminar==true) {
            mFusedLocationClient!!.removeLocationUpdates(locationCallback!!)
        }

    }

    //method see restaurants
    private fun seeRestaurants(drawerLayout: DrawerLayout) {
        //We save the json data in a list and go through it location by location
        val locations = Location.readLocations(this)
        for (location: Location in locations) {

            //we only include restaurants
            if (location.tag.equals("restaurant")) {
                val miUbicacion = LatLng(location.latitude, location.longitude) //ubication of restaurant
                var xd = mMap.addMarker(
                    MarkerOptions().snippet(location.fragment).icon(
                        BitmapDescriptorFactory.fromResource(R.drawable.cuchilleria)
                    ).position(miUbicacion).title(location.title) //marker of restaurant with icon,tittle,snippet...
                )
                xd.tag = "restaurant" //add tag to the marker
                listaRes.add(xd)//add marker to a list for after remove it
                drawerLayout.closeDrawers(); // So is more beutiful :)
            }
        }
    }

    //Absolutely the same as above but with bookmakers
    private fun seeApostasy(drawerLayout: DrawerLayout) {

        val locations = Location.readLocations(this)

        for (location: Location in locations) {

            if (location.tag.equals("apostasy")) {
                val miUbicacion = LatLng(location.latitude, location.longitude)
                var xd = mMap.addMarker(
                    MarkerOptions().snippet(location.fragment).icon(
                        BitmapDescriptorFactory.fromResource(R.drawable.apuesta)
                    ).position(miUbicacion).title(location.title)
                )
                xd.tag = "apostasy"
                listaApo.add(xd)
                drawerLayout.closeDrawers();
            }
        }
    }

    //this method goes through all the lists and deletes the markers, thus making them disappear
    private fun seeOcultar(drawerLayout: DrawerLayout) {

        val locations = Location.readLocations(this)

        for (location: Location in locations) {

            if(eliminar==true) {
                removeLocations()
            }
            for (i in lista) {
                i.remove()
            }
            for (i in listaRes) {
                i.remove()
            }
            for (i in listaApo) {
                i.remove()
            }
            drawerLayout.closeDrawers();
        }
    }


}