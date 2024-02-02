package Entidades
import java.util.*

class Atleta(
    val nombre: String,
    var habilitadoParaParticipar: Boolean,
    var altura: Float,
    var fechaNacimiento: Date,
    var edad: Int
) {
    override fun toString(): String {
        return "\nNombre: $nombre " +
                "\nHabilitado para participar: $habilitadoParaParticipar " +
                "\nAltura: $altura" +
                "\nFecha de nacimiento: $fechaNacimiento " +
                "\nEdad: $edad\n"
    }
}
