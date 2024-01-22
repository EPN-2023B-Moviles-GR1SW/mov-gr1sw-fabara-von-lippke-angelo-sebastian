package com.example.exame_01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.example.exame_01.modelos.UsoDeporte

class AtletaUso : AppCompatActivity() {
    lateinit var inputValor: TextInputEditText
    lateinit var inputMateria: TextInputEditText
    lateinit var inputCodMateria: TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar_deporte)



        val btnCrear = findViewById<Button>(R.id.btn_crear_nota)
        obtenerDatos()
        btnCrear.setOnClickListener {
            logicaCrear()
        }


    }

    fun obtenerDatos(){
        inputValor = findViewById(R.id.input_valor)
        inputMateria = findViewById(R.id.input_materia)
        inputCodMateria = findViewById(R.id.input_codMateria)
    }
    fun logicaCrear(){
        val id: Int = intent.extras?.getInt("id")!!

        val lista = UsoDeporte.obtenerLista()

        val inputNota:String = inputValor.text.toString()
        var altura: Double = 0.0
        val nombre = inputMateria.text.toString()
        val cod = inputCodMateria.text.toString()
        var codMat: Int = 0


        try {
            altura= inputNota.toDoubleOrNull()!!
            codMat = cod.toInt()
            if (altura != null) {
                lista[id]?.agregarAtleta(nombre, altura, codMat)
                Toast.makeText(this," Atleta ingresado", Toast.LENGTH_SHORT).show()
                val intend = Intent(this, MainActivity::class.java)
                startActivity(intend)
            }
        } catch (e: NumberFormatException) {
            println(e)
        }

    }
}