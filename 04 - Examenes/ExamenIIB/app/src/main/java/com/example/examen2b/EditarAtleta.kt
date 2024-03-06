package com.example.examenib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.examenib.databasefrs.FirestoreAtleta
import com.example.examenib.model.Atleta
import java.util.Date

class EditarAtleta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_producto)

        val id = findViewById<EditText>(R.id.inp_editarIdProducto)
        val nombre = findViewById<EditText>(R.id.inp_editarNombre)
        val desc = findViewById<EditText>(R.id.inp_editarDesc)
        val precio = findViewById<EditText>(R.id.inp_editarPrecio)

        var idProductoSeleccionado = intent.getStringExtra("idProducto");

        FirestoreAtleta.consultarProducto(idProductoSeleccionado!!){
            id.setText(it.id)
            nombre.setText(it.nombre)
            desc.setText(it.descripcion)
            precio.setText(it.precio.toString())
        }


        val btnEditarProducto = findViewById<Button>(R.id.btn_editar_prodexist)
        btnEditarProducto.setOnClickListener{
            editarProducto()
            irActividad(ListViewAtleta::class.java)
        }

        val btnCancelar = findViewById<Button>(R.id.btn_cancelar_editarprod)
        btnCancelar.setOnClickListener {
            irActividad(ListViewAtleta::class.java)
        }


    }

    fun editarProducto() {
        val id = findViewById<EditText>(R.id.inp_editarIdProducto)
        val nombre = findViewById<EditText>(R.id.inp_editarNombre)
        val desc = findViewById<EditText>(R.id.inp_editarDesc)
        val precio = findViewById<EditText>(R.id.inp_editarPrecio)

        val atletaActualizado = Atleta(id.text.toString(),
            nombre.text.toString(),
            desc.text.toString(),
            precio.text.toString().toLong(),
            Date())
        FirestoreAtleta.actualizarProductos(atletaActualizado)

    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}