package com.jovian.p7mymaps

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.text.SpannableString
import android.text.style.TextAppearanceSpan
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.jovian.p7mymaps.databinding.ActivityDrawerBinding
import kotlin.system.exitProcess

const val REQUEST_PERSMISSION_CODE = 1000

class DrawerActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener, NavigationView.OnNavigationItemSelectedListener {

    //variables varias
    private lateinit var binding: ActivityDrawerBinding
    private lateinit var mMap: GoogleMap
    private lateinit var drawerLayout: DrawerLayout

    //variables para miLocalizacion
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private var myLatitude = 0.0
    private var myLongitude = 0.0
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    //variables para almacenar los distintos listados
    private var misPosiciones: MutableList<Marker> = mutableListOf()
    private var misArrocerias: MutableList<Marker> = mutableListOf()
    private var misFarmacias: MutableList<Marker> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        // Obtiene el SupportMapFragment y notifica cuando el mapa esta listo para su uso
        //importante para que carge el fragmento del mapa con la localizacion inicial
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        drawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        //esta parte de codigo es para aplicar estilo a los diferentes items del menu
        //se crean 2 nuevos estilos en themes para aplicarles uno en concreto
        val mainOptions = listOf(R.id.option1, R.id.option3, R.id.option4)
        val menu = navView.menu
        val secOptions = listOf(R.id.option2, R.id.option5, R.id.option6, R.id.option7)
        for(option in mainOptions){
            val tools = menu.findItem(option)

            val s = SpannableString(tools.title)
            s.setSpan(TextAppearanceSpan(this, R.style.MainOptionStyle), 0, s.length, 0)
            tools.title = s
        }

        for(option in secOptions){
            val tools = menu.findItem(option)

            val s = SpannableString(tools.title)
            s.setSpan(TextAppearanceSpan(this, R.style.SecondaryOptionStyle), 0, s.length, 0)
            tools.title = s
        }


        val mActionBarDrawerToggle = ActionBarDrawerToggle(this,
            drawerLayout, binding.appBarMain.toolbar, R.string.drawer_opened, R.string.drawer_closed )
        mActionBarDrawerToggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        //esta parte de codigo es para usar mi localizacion
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        locationRequest = LocationRequest.create()
        locationRequest. priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        locationRequest. interval = 16000 // 16seconds
        locationRequest. fastestInterval = 8000 // 8seconds
        locationRequest.smallestDisplacement = 0f


        //en esta parte obtenemos nuestra localizacion
        //en mi caso, una vez obtenidas las coordenadas, las paso a otra funcion
        locationCallback = object: LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult){
                super.onLocationResult(locationResult)
                for(location in locationResult.locations){
                    if(location != null){
                        myLatitude = location.latitude
                        myLongitude = location.longitude
                        //desde el ordenador siempre me lleva al estado de Florida en los USA (ojala fuera verdad)
                        //como no consigo habilitar en el ordenador los permisos de localizacion de android studio
                        //y cada vez que entre a este metodo me va a llevar al mismo sitio, le pongo una direccion concreta dentro de mi localidad de residencia
                        //Falta probar con un dispositivo movil
                        if(myLatitude == 37.4218964 && myLongitude == -122.0840582)  verMiLocalizacion(39.4333111, -0.482594)
                        else verMiLocalizacion(myLatitude, myLongitude)
                    }
                }
            }
        }
    }

    /**
     * funcion inicial de la carga de mapa
     */
    override fun onMapReady(p0: GoogleMap) {

        //variable para manipular las opciones del mapa
        mMap = p0

        // Situamos la marca en al ayto. de Torrent
        val torrent = LatLng(39.4298814, -0.4784719)
        //movemos la camara con el zoom maximo que nos indican en la practica.Anteriormente hemos quitado la marca
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(torrent,12.0f))
        //Deshabilitamos el control del zoom
        mMap.uiSettings.isZoomControlsEnabled = false
        //Deshabilitamos la rotacion con los dedos
        mMap.uiSettings.isCompassEnabled = false
        //aplicamos un estilo json al mapa
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json))
        //activamos el listener por si apareciese la opcion de clickar sobre el marker
        mMap.setOnInfoWindowClickListener(this)
    }

    //funcion para ver la informacion de las ubicaciones al pulsar sobre sus respectivos markers
    override fun onInfoWindowClick(marker: Marker) {

        //primero mostramos la etiqueta sobre nuestro marcador
        marker.showInfoWindow()

        //comprobamos el tag de la etiqueta, y segun lo que indique , realizamos una accion
        //para mi posicion, mostramos un alert con nuestras coordenadas
        if (marker.tag?.equals("Estoy aqui") == true) {
            val dialog = AlertDialog.Builder(this@DrawerActivity)
                .setTitle("myMaps").setIcon(R.drawable.treasure)
                .setMessage("Su ubicacion actual es: \n\n " + "Lat: " + marker.position.latitude + " Lon: " + marker.position.longitude)
                .setPositiveButton("Ok") { view, _ ->
                    view.dismiss()
                }
                .setCancelable(false) //not need canceled button
                .create()

            dialog.show()

            //para las farmacias, llamaremos por telefono
        }  else if (marker.tag?.equals("farmacia") == true) {


        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:" + marker.snippet)
        startActivity(intent)

            //para las arrocerias, veremos su pagina web
        } else if (marker.tag?.equals("Arroceria") == true) {

            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(marker.snippet)
            startActivity(intent)

        }
    }

    /**
     * metodo para controlar las diferentes opciones del menu
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.option1) requestLocations()
        if(item.itemId == R.id.option2) removeLocations()
        if(item.itemId == R.id.option3) verArrocerias()
        if(item.itemId == R.id.option4) verFarmacias()
        if(item.itemId == R.id.option5) borrarTodo()
        if(item.itemId == R.id.option6) cerrarMenu()
        if(item.itemId == R.id.option7) cerrarAplicacion()
        return true

    }

    //metodo para manejar permisos para mostrar mi localizacion
    private fun requestLocations() {
        if (ActivityCompat.checkSelfPermission(this@DrawerActivity,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this@DrawerActivity,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this@DrawerActivity,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION),
                REQUEST_PERSMISSION_CODE)
        } else {
            mFusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback, Looper.getMainLooper())
        }
    }

    //funcion para borrar mi localizacion
    private fun removeLocations() {

        //cancelamos nuestro seguimiento
        mFusedLocationClient.removeLocationUpdates(locationCallback)

        //borramos todas las ubicaciones de la lista
        for(marker in misPosiciones){
            marker.remove()
        }

    }

    /**
     * funcion para mostrar mi localizacion
     * @param mLatitude latitud de mi ubicacion
     * @param mLongitude longitud de mi ubicacion
     */
    private fun verMiLocalizacion(mLatitude: Double, mLongitude: Double) {

        // Situamos la marca donde estoy
        val miPos = LatLng(mLatitude, mLongitude)
        //ponemos la marca
        val marker= mMap.addMarker(MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.micara)).position(miPos).title("Mi Ubicacion"))
        //añadimos un tag a la marca
        if (marker != null) marker.tag = "Estoy aqui"
        //añadimos la posicion a la lista
        if (marker != null) misPosiciones.add(marker)
        //movemos la camara con el zoom maximo que nos indican en la practica.Anteriormente hemos quitado la marca
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(miPos,18.0f))
        //llamada a la funcion para que muestre nuestra info, que será un alert
        mMap.setOnInfoWindowClickListener(this)

    }

    //metodo para cerrar el menu. No he encotrado algo mejor, asi que lo bloqueo, se oculta y lo desbloqueo
    private fun cerrarMenu() {

        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)

    }

    //metodo para cerrar la aplicacion
    private fun cerrarAplicacion(){
        exitProcess(-1)
    }

    //metodo para ver arrocerias
    private fun verArrocerias() {

        //empezamos a leer la lista xml con todos los sitios
        val sitios = Location.readLocations(this)

        //para cada uno de los sitios, comprobamos que sea una arroceria
        for (sitio: Location in sitios) {

            //debemos filtrar para coger solo las arrocerias y almacenarlas en la lista correspondiente con sus atributos
            if (sitio.tag == "Arroceria") {
                val ubicacion = LatLng(sitio.latitude, sitio.longitude)
                val marcador = mMap.addMarker(
                    MarkerOptions().snippet(sitio.fragment).icon(
                        BitmapDescriptorFactory.fromResource(R.drawable.plato)
                    ).position(ubicacion).title(sitio.title)
                )

                //añadimos un tag al marcador
                if (marcador != null) marcador.tag = "Arroceria"
                //añadimos el marcador a la lista
                if (marcador != null) misArrocerias.add(marcador)
            }
        }
    }

    //metodo para ver farmacias
    private fun verFarmacias() {

        //leemos la lista completa de ubicaciones del fichero xml
        val sitios = Location.readLocations(this)
        for (sitio: Location in sitios) {

            //debemos filtrar para coger solo las farmacias y almacenarlas en la lista correspondiente con sus atributos
            if (sitio.tag == "farmacia") {
                val ubicacion = LatLng(sitio.latitude, sitio.longitude)
                val marcador = mMap.addMarker(
                    MarkerOptions().snippet(sitio.fragment).icon(
                        BitmapDescriptorFactory.fromResource(R.drawable.medicina)
                    ).position(ubicacion).title(sitio.title)
                )

                //añadimos un tag al marcador
                if (marcador != null) marcador.tag = "farmacia"
                //añadimos el marcador a la lista
                if (marcador != null) misFarmacias.add(marcador)
            }
        }
    }

    //metodo para borrar los marcadores de arrocerias y farmacias
    private fun borrarTodo(){

        //cancelamos nuestro seguimiento
        mFusedLocationClient.removeLocationUpdates(locationCallback)

        //borramos todas las ubicaciones de la lista
        for(marker in misFarmacias){
            marker.remove()
        }

        for(marker in misArrocerias){
            marker.remove()
        }
    }
}

