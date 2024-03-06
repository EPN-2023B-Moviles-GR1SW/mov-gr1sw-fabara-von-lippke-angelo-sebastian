package com.example.deber3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.deber3.Data.BaseDatos

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniciarRecyclerView()
    }
    fun iniciarRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.rv_correos)
        val adaptador = RecylerViewAdapterGmail(
            this,
            BaseDatos.arregloCorreos,
            recyclerView
        )
    }
}