package CasosDeUsos

import Entidades.Atleta
import Repositorios.AtletasRepositorio

class AtletasUso {
    val archivo = AtletasRepositorio()

    fun agregarAtleta(atleta: Atleta): Boolean {

        if (archivo.obtenerAtletaPorNombre(atleta.nombre) != null) {
            println("El atleta ya se encuentra registrado")
            return false
        }

        archivo.agregarAtleta(atleta)
        println("Registro exitoso")
        return true
    }

    fun obtenerAtletas(): List<Atleta> {
        return archivo.obtenerAtletas()
    }

    fun eliminarAtletaPorNombre(nombre: String): Boolean {
        if (archivo.obtenerAtletaPorNombre(nombre) == null) {
            println("Atleta no encontrado")
            return false
        }
        archivo.eliminarAtletaPorNombre(nombre)
        println("Eliminación exitosa")
        return true
    }

    fun actualizarAtleta(nombre: String, dto: Atleta): Boolean {
        if (archivo.obtenerAtletaPorNombre(nombre) == null) {
            println("Atleta no encontrado")
            return false
        }

        archivo.actualizarAtleta(nombre, dto)
        println("Actualización exitosa")
        return true
    }
}
