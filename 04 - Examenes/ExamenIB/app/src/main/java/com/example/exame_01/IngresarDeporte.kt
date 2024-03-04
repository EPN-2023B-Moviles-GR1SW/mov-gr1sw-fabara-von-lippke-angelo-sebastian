package com.example.exame_01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.example.exame_01.modelos.UsoDeporte

class IngresarDeporte : AppCompatActivity() {
    lateinit var inputNombre: TextInputEditText
    lateinit var inputApellido: TextInputEditText
    lateinit var inputCurso: TextInputEditText
    lateinit var inputParalelo: TextInputEditText
    lateinit var inputDiaCum: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar_deporte)
        obtenerDatos()

        val btnCrear = findViewById<Button>(R.id.btn_crear)
        btnCrear.setOnClickListener {
            if(verificarDatos()){
                UsoDeporte.agregarDeporte(inputNombre.text.toString(),inputApellido.text.toString().toFloat())
                Toast.makeText(this, "Atleta creado Creado", Toast.LENGTH_SHORT).show()
                val intend = Intent(this, MainActivity::class.java)
                startActivity(intend)
            }else{
                Toast.makeText(this, "Complete los datos", Toast.LENGTH_SHORT).show()
            }

        }

    }
    fun obtenerDatos(){
        inputNombre= findViewById(R.id.input_nombre)
        inputApellido = findViewById(R.id.input_precio)
        inputCurso = findViewById(R.id.input_porciones)
        inputParalelo = findViewById(R.id.input_paralelo)
        inputDiaCum = findViewById(R.id.input_agregacion)
    }
    fun verificarDatos(): Boolean {
        if (inputNombre.text?.isEmpty() == true || inputApellido.text?.isEmpty() == true)
        {
            val inputLayout = findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.layout_nombre)
            inputLayout.error = "Este campo es obligatorio"
            val inputLayout2 = findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.layout_apellido)
            inputLayout2.error = "Este campo es obligatorio"
            return false
        }else{
            return true
        }
    }
}