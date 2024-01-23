package com.example.b2023_gr1sw_asfv

import android.content.pm.PackageManager
import android.icu.text.CaseMap.Title
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebSettings.ZoomDensity
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.material.snackbar.Snackbar

class GGoogleMapsActivity : AppCompatActivity() {
    private lateinit var mapa: GoogleMap
    var permisos = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ggoogle_maps)
        solicitarPermisos()
        iniciarLogicaMapa()
        val boton = findViewById<Button>(R.id.btn_ir_carolina)
        boton.setOnClickListener {
            irCarolina()
        }
    }
    fun irCarolina(){
        val carolina = LatLng(
            -0.1825684318486696,
            -78.48447277600916
        )
        val zoom = 17f
        moverCamaraConZoom(carolina, zoom)
    }
    fun mostrarSnackbar(texto: String){
        Snackbar
            .make(
                findViewById(R.id.cl_google_maps),
                texto,
                Snackbar.LENGTH_LONG
            )
            .show()
    }

    fun iniciarLogicaMapa(){
        val fragmentoMapa =  supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        fragmentoMapa.getMapAsync{ googleMap ->

            with(googleMap){
                mapa = googleMap
                establecerConfiguracionMapa()
                moverQuicentro()
                anadirPolilinea()
                anadirPoligono()
                escucharListeners()
            }
        }
    }
    fun escucharListeners(){
        mapa.setOnPolygonClickListener {
            mostrarSnackbar("setOnPolygonClickListener ${it.tag}")
            it.tag
        }
        mapa.setOnPolygonClickListener {
            mostrarSnackbar("setOnPolylineClickListener ${it.tag}")
            it.tag
        }
        mapa.setOnMarkerClickListener {
            mostrarSnackbar("setOnMarketClickListener ${it.tag}")
            it.tag
            return@setOnMarkerClickListener true
        }
        mapa.setOnCameraMoveListener {
            Log.i("mapa","setOnCameraMoveListener")
        }
        mapa.setOnCameraMoveStartedListener {
            Log.i("mapa","setOnCameraMoveStartedListener ${it}")
        }
        mapa.setOnCameraMoveListener {
            Log.i("mapa","setOnCameraIdleListener")
        }
    }
    fun anadirPolilinea(){
        with(mapa){
            val poliLeneaUno = mapa
                .addPolyline(
                    PolylineOptions()
                        .clickable(true)
                        .add(
                            LatLng(-0.1759187040647396,
                                -78.48506472421384),
                            LatLng(-0.17632468492901104,
                                -78.48265589308046),
                            LatLng(-0.17746143130181483,
                                -78.4770533307815)
                        )
                )
            poliLeneaUno.tag="linea-1"
        }
    }
    fun anadirPoligono(){
        with(mapa){
            val poligonoUno = mapa
                .addPolygon(
                PolygonOptions()
                    .clickable(true)
                    .add(
                        LatLng(-0.1759187040647396,
                            -78.48506472421384),
                        LatLng(-0.17632468492901104,
                            -78.48265589308046),
                        LatLng(-0.17746143130181483,
                            -78.4770533307815)
                    )
            )
            poligonoUno.fillColor= -0xc771c4
            poligonoUno.tag = "poligono-1"
        }
    }
    fun moverQuicentro(){
        val zoom = 17f
        val quicentro =  LatLng(
            -0.17556708490271092, -78.48014901143776
        )
        val titulo = "Quicentro"
        val markQuicentro = anadirMarcador(
            quicentro, titulo
        )
        markQuicentro.tag = titulo
        moverCamaraConZoom(quicentro, zoom)
    }
    fun anadirMarcador(latLng: LatLng, title: String): Marker {
        return mapa.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(title)
        )!!
    }
    fun moverCamaraConZoom(latLng: LatLng, zoom: Float = 10f){
        mapa.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(latLng, zoom)
        )
    }
    fun establecerConfiguracionMapa(){
        val contexto = this.applicationContext
        with(mapa) {
            val permisosFineLocation = ContextCompat
                .checkSelfPermission(
                    contexto,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            val tienePermisos = permisosFineLocation == PackageManager
                .PERMISSION_GRANTED
            if(tienePermisos){
                mapa.isMyLocationEnabled = true
                uiSettings.isMyLocationButtonEnabled =  true
            }
            uiSettings.isZoomControlsEnabled = true
        }
    }

    fun solicitarPermisos(){
        val contexto = this.applicationContext
        val permisoFineLocation =android.Manifest.permission.ACCESS_FINE_LOCATION
        val permisoCoarse =  android.Manifest.permission.ACCESS_COARSE_LOCATION
        val permisosFineLocation =  ContextCompat
            .checkSelfPermission(
                contexto,
                permisoFineLocation
            )
        val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
        if(tienePermisos){
            permisos = true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    permisoFineLocation,permisoCoarse
                ),
                1
            )
        }
    }
}