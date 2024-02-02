package Repositorios

abstract class Repositorio() {
    abstract protected var rutaRepositorio: String

    abstract protected fun cargarDatos()

    abstract protected fun guardarDatos()
}

