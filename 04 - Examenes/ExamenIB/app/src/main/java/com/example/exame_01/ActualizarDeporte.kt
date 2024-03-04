package com.example.exame_01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import java.lang.Exception
import com.example.exame_01.modelos.UsoDeporte

class ActualizarDeporte : AppCompatActivity() {
    lateinit var inputNombre: TextInputEditText
    lateinit var inputPrecio: TextInputEditText
    lateinit var inputParalelo: TextInputEditText
    lateinit var inputPorciones: TextInputEditText
    lateinit var inputDiaCum: TextInputEditText

    var id: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_deporte)
        id = intent.extras?.getInt("id")!!
        ponerDatos()

        val btnEditar = findViewById<Button>(R.id.btn_editar)
        btnEditar.setOnClickListener {
            if(verificarDatos()){
                try {
                    UsoDeporte.editarDeporte(id,
                        inputNombre.text.toString(),
                        inputPrecio.text.toString(),
                    )
                }catch (e: Exception){
                    println(e)
                }

                Toast.makeText(this, "Estudiante Editado Correctamente", Toast.LENGTH_SHORT).show()
                val intend = Intent(this, MainActivity::class.java)
                startActivity(intend)
            }else{
                Toast.makeText(this, "Complete los datos", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun ponerDatos(){
        inputNombre= findViewById(R.id.input_nombre)
        inputPrecio = findViewById(R.id.input_precio)
        inputParalelo = findViewById(R.id.input_paralelo)
        inputPorciones = findViewById(R.id.input_porciones)
        inputDiaCum = findViewById(R.id.input_agregacion)

        val lista = UsoDeporte.obtenerLista()
        inputNombre.setText(lista[id]!!.nombre)
        inputPrecio.setText(lista[id]!!.tiempoPromedioPartido.toString())
        lista[id]!!.esDeporteIndividual?.let { inputParalelo.setText(it.toString()) }
        inputPorciones.setText(lista[id]!!.numeroJugadores)
        lista[id]!!.fechaRegistro?.let { inputDiaCum.setText(it.toString()) }

    }
    fun verificarDatos(): Boolean {
        if (inputNombre.text?.isEmpty() == true || inputPrecio.text?.isEmpty() == true)
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