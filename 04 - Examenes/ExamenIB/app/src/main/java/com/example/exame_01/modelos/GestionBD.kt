package com.example.exame_01.modelos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.exame_01.R

class GestionBD(private val context: Context, private val datos: MutableMap<Int, Deporte>) : BaseAdapter() {

    override fun getCount(): Int {
        return datos.size
    }

    override fun getItem(position: Int): Deporte? {
        return datos[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var vista = convertView
        if (vista == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            vista = inflater.inflate(R.layout.activity_mostrar_lista, null)
        }

        // Personaliza la vista con los datos del elemento en la posición actual
        val tv_nombre = vista?.findViewById<TextView>(R.id.tv_nombre)
        val tv_apellido = vista?.findViewById<TextView>(R.id.tv_precio)
        tv_nombre?.text = datos[position + 1]?.nombre
        tv_apellido?.text = datos[position + 1]?.tiempoPromedioPartido.toString()
        return vista!!
    }

}