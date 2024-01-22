package com.example.exame_01.modelos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.exame_01.modelos.Atleta
import com.example.exame_01.R
class BDAtletas (private val context: Context, private val datos: MutableList<Atleta>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return datos.size
    }

    override fun getItem(position: Int): Atleta? {
        return datos[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var vista = convertView
        if (vista == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            vista = inflater.inflate(R.layout.activity_mostrar_atleta_deporte, null)
        }

        // Personaliza la vista con los datos del elemento en la posición actual
        val tv_nota = vista?.findViewById<TextView>(R.id.tv_precio_ingrediente)
        val tv_ingrediente = vista?.findViewById<TextView>(R.id.tv_ingrediente)
        val tv_sipasa = vista?.findViewById<TextView>(R.id.tv_sipasa)
        tv_ingrediente?.text = "${datos[position]?.nombre.toString()}:"
        tv_nota?.text = datos[position]?.altura.toString()
        if (datos[position]?.habilitadoParaParticipar == true) {
            tv_sipasa?.text = "Pasa"
        } else {
            tv_sipasa?.text = "No Pasa"
        }

        return vista!!
    }
}