package CasosDeUsos

import Entidades.Deporte
import Repositorios.AtletasRepositorio
import Repositorios.DeportesRepositorio

class DeportesUso {
    val repositorio = DeportesRepositorio()
    val atletas = AtletasRepositorio()

    fun agregarDeporte(deporte: Deporte): Boolean {

        if (repositorio.obtenerDeportePorNombre(deporte.nombre) != null) {
            println("Deporte ya registrado")
            return false
        }

        repositorio.agregarDeporte(deporte)
        println("Registro agregado exitosamente")
        return true
    }

    fun obtenerDeportes(): List<Deporte> {
        return repositorio.obtenerDeportes()
    }

    fun eliminarDeportePorNombre(nombre: String): Boolean {
        if (repositorio.obtenerDeportePorNombre(nombre) == null) {
            println("Deporte no encontrado")
            return false
        }
        repositorio.eliminarDeportePorNombre(nombre)
        println("Eliminación exitosa")
        return true
    }

    fun actualizarDeporte(nombre: String, dto: Deporte): Boolean {
        if (repositorio.obtenerDeportePorNombre(nombre) == null) {
            println("Deporte no encontrado")
            return false
        }

        repositorio.actualizarDeporte(nombre, dto)
        println("Actualización exitosa")
        return true
    }

    fun agregarAtleta(deporte: String, nombreAtleta: String): Boolean {
        if (repositorio.obtenerDeportePorNombre(deporte) == null) {
            println("Deporte no encontrado")
            return false
        }
        if (atletas.obtenerAtletaPorNombre(nombreAtleta) == null) {
            println("Atleta no encontrado")
            return false
        }

        val atleta = atletas.obtenerAtletaPorNombre(nombreAtleta)
        if (atleta != null) {
            repositorio.agregarAtleta(deporte, atleta)
            println("Atleta agregado con éxito")
            return true
        }
        return false
    }

    fun eliminarAtleta(deporte: String, nombreAtleta: String): Boolean {
        if (repositorio.obtenerDeportePorNombre(deporte) == null) {
            println("Deporte no encontrado")
            return false
        }
        if (atletas.obtenerAtletaPorNombre(nombreAtleta) == null) {
            println("Atleta no encontrado")
            return false
        }

        val atleta = atletas.obtenerAtletaPorNombre(nombreAtleta)
        if (atleta != null) {
            repositorio.eliminarAtleta(deporte, atleta)
            println("Eliminación con éxito")
            return true
        }
        return false
    }
}
