package com.example.exame_01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.PopupMenu
import android.widget.Toast
import com.example.exame_01.modelos.GestionBD
import com.example.exame_01.modelos.UsoDeporte

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initProgram()

    }

    override fun onResume() {
        super.onResume()
        initProgram()
    }

    fun initProgram() {

        val lista = UsoDeporte.obtenerLista()
        val miadapter = GestionBD(this,lista)
        val lv1 = findViewById<ListView>(R.id.lv_est)
        lv1.adapter = miadapter

        lv1.setOnItemClickListener { _, view, position, _ ->
            // Obtén la vista del ícono del menú
            val menuIcon = view.findViewById<androidx.cardview.widget.CardView>(R.id.cv_item)
            // Muestra el menú contextual
            showPopupMenu(menuIcon, position + 1, miadapter )
        }
        val btnCrearEstudiante = findViewById<Button>(R.id.btn_crear_nuevo)

        btnCrearEstudiante.setOnClickListener {
            val intend = Intent(this, IngresarDeporte::class.java)
            startActivity(intend)
        }


    }
    fun showPopupMenu(view: View, position: Int, miadapter: GestionBD) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.inflate(R.menu.menu_atletas)

        popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.opcion1 -> {
                    val intend = Intent(this, ActualizarDeporte::class.java)
                    Log.i("iddd", position.toString())
                    intend.putExtra("id", position)
                    startActivity(intend)
                    true
                }
                R.id.opcion2 -> {
                    val intend = Intent(this, AtletaUso::class.java)
                    Log.i("iddd", position.toString())
                    intend.putExtra("id", position)
                    startActivity(intend)
                    true
                }
                R.id.opcion3 -> {
                    UsoDeporte.eliminarEstudiante(position)
                    Toast.makeText(this, "Estudiante Eliminado", Toast.LENGTH_SHORT).show()
                    miadapter.notifyDataSetChanged()
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }
}