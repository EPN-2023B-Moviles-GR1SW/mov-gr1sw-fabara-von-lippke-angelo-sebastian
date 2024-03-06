package com.example.examenib
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import com.example.examenib.databasefrs.FirestoreDeporte
import com.example.examenib.model.Deporte
import java.util.Date

class CrearDeporte : AppCompatActivity() {

    var radioT:RadioButton?=null
    var radioF:RadioButton?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_resenia)
        var idProducto = intent.getStringExtra("idProducto")

        radioT = findViewById(R.id.rdb_recom_true_editar)
        radioF = findViewById(R.id.rdb_recom_false_editar)
        val btnCrearNuevaResenia = findViewById<Button>(R.id.btn_crear_nuevares)
        btnCrearNuevaResenia.setOnClickListener{
            crearNuevaResenia()
            irActividad(ListViewDeporte::class.java, idProducto!!)
        }

        val btnCancelar = findViewById<Button>(R.id.btn_cancelar_crearres)
        btnCancelar.setOnClickListener{irActividad(ListViewAtleta:: class.java,idProducto!!)}
    }

    fun crearNuevaResenia(){
        val id = findViewById<EditText>(R.id.inp_idResenia)
        val comentario = findViewById<EditText>(R.id.inp_comentario)
        val calificacion = findViewById<EditText>(R.id.inp_calificacion)
        val recomendado: Boolean = radioT?.isChecked == true
        var idProducto = intent.getStringExtra("idProducto")

        val nuevaDeporte = Deporte(id.text.toString(),comentario.text.toString(),calificacion.text.toString().toLong(), recomendado, Date())
        FirestoreDeporte.crearResenia(nuevaDeporte, idProducto!!)
    }


    fun irActividad (
        clase: Class <*>, idProducto: String
    ) {
        val intent = Intent(this, clase)
        intent.putExtra("idProducto", idProducto)
        startActivity(intent)
    }

}