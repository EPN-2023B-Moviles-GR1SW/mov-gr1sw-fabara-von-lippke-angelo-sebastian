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
import android.widget.TextView
import android.widget.Toast
import com.example.exame_01.modelos.Atleta
import com.example.exame_01.modelos.UsoDeporte
import com.example.exame_01.modelos.BDAtletas
class Atletas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atletas)

        initProgram()

    }

    fun initProgram(){
        val tvResult = findViewById<TextView>(R.id.tv_est)

        val id: Int? = intent.extras?.getInt("id")

        val lista = UsoDeporte.obtenerLista()


        val nombre = lista[id]?.nombre
        val tiempo = lista[id]?.tiempoPromedioPartido

        //val id: String = intent.extras?.getString("id").orEmpty()
        tvResult.text = "$nombre $tiempo"

        val ingredientes:MutableList<Atleta>? = lista[id]?.obtenerAtletas()
        Log.i("deporte", ingredientes.toString())

        if(ingredientes?.isNotEmpty() == true){
            val adaptadorCal = ingredientes?.let { BDAtletas(this, it) }
            val lv2 = findViewById<ListView>(R.id.lv_cal)
            lv2.adapter = adaptadorCal

            lv2.setOnItemClickListener { _, view, position, _ ->

                val menuIcon = view.findViewById<androidx.cardview.widget.CardView>(R.id.cv_nota)
                showPopupMenu(menuIcon, position)
            }
        }else{
            val tv_exep = findViewById<TextView>(R.id.tv_exep)
            tv_exep.text = "No tiene ingredientes"
        }

        val btnNuevaNota = findViewById<Button>(R.id.btn_nueva_cal)
        btnNuevaNota.setOnClickListener {
            val intend = Intent(this, AtletaUso::class.java)
            intend.putExtra("id", id)
            startActivity(intend)
        }
    }
    fun showPopupMenu(view: View, position: Int) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.inflate(R.menu.menu_atletas)

        popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.opc1-> {
                    Toast.makeText(this, "Atleta agregado", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.opc2 -> {
                    Toast.makeText(this, "Atleta Eliminado", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false
            }
        }
        popupMenu.show()
    }
}