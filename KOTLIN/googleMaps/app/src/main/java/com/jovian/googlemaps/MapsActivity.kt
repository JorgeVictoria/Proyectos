package com.jovian.googlemaps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.TextAppearanceSpan
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.ui.AppBarConfiguration

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.navigation.NavigationView
import com.jovian.googlemaps.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener, NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setSupportActionBar(binding.appBarMain.toolbar)


        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        val mainOptions = listOf(R.id.option1, R.id.option4)
        val menu = navView.menu
        val secOptions = listOf(R.id.option2, R.id.option3, R.id.option5, R.id.option6)
        for(option in mainOptions){
            val tools = menu.findItem(option)

            //tools.icon.setTint(getColor(R.color.black))

            val s = SpannableString(tools.title)
            s.setSpan(TextAppearanceSpan(this, R.style.MainOptionStyle), 0, s.length, 0)
            tools.title = s
        }

        for(option in secOptions){
            val tools = menu.findItem(option)

            tools.icon.setTint(getColor(R.color.black))

            val s = SpannableString(tools.title)
            s.setSpan(TextAppearanceSpan(this, R.style.SecondaryOptionStyle), 0, s.length, 0)
            tools.title = s
        }


        val mActionBarDrawerToggle = ActionBarDrawerToggle(this,
            drawerLayout, binding.appBarMain.toolbar, R.string.drawer_opened, R.string.drawer_closed )
        mActionBarDrawerToggle.syncState()



        navView.setNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Toast.makeText(this, "${item.itemId}", Toast.LENGTH_SHORT).show()
        return true
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

    }

    override fun onInfoWindowClick(p0: Marker?) {
        TODO("Not yet implemented")
    }


}


