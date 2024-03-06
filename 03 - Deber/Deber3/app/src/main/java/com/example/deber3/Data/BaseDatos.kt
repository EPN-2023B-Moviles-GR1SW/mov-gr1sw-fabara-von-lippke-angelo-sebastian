package com.example.deber3.Data

import com.example.deber3.Model.Correo
import com.example.deber3.R

class BaseDatos {
    companion object{
        val arregloCorreos = arrayListOf<Correo>()
        init {
            arregloCorreos.add(Correo("CLAVEMAT", "Invitación a conferencia", "¡Oportunidad para los estudiantes de...", R.drawable.comp1))
            arregloCorreos.add(Correo("AQUIAMBI", "Actualización de datos", "Te invitamos a participar en la conferencia sobre...", R.drawable.comp2))
            arregloCorreos.add(Correo("BIRPAM", "Recordatorio de evento", "Regístrate en nuestro taller de investigación para...", R.drawable.comp3))
            arregloCorreos.add(Correo("NROGCOM", "Oferta de empleo", "¡Gran oportunidad de trabajo como programador en...", R.drawable.comp4))
            arregloCorreos.add(Correo("VATINTEG", "Convocatoria para tutores", "Por favor, actualiza tus datos en nuestro sistema antes...", R.drawable.comp5))
            arregloCorreos.add(Correo("RONCITFRA", "Taller de investigación", "Recuerda que el evento sobre la transformación digital...", R.drawable.comp6))
        }
    }
}