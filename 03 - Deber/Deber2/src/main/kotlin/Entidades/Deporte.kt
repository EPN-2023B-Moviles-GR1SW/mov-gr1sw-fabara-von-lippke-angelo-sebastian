package Entidades

import java.util.*
import kotlin.collections.ArrayList

class Deporte(
        val nombre: String,
        var esDeporteIndividual: Boolean,
        var tiempoPromedioPartido: Float,
        var fechaRegistro: Date,
        var numeroJugadores: Int
) {
    var atletas: ArrayList<Atleta> = ArrayList()

    override fun toString(): String {
        var resultadoAtletas: String = ""
        if (atletas.size != 0)
            resultadoAtletas = atletas.joinToString("; ") { it.nombre }

        return "\nNombre: $nombre " +
                "\nDeporte individual: $esDeporteIndividual " +
                "\nTiempo promedio de un partido: $tiempoPromedioPartido" +
                "\nFecha de registro como deporte olímpico: $fechaRegistro " +
                "\nNúmero de jugadores en un equipo: $numeroJugadores" +
                "\nAtletas: [$resultadoAtletas]\n"
    }
}
