import Entidades.Atleta
import Entidades.Deporte
import CasosDeUsos.AtletasUso
import CasosDeUsos.DeportesUso
import java.text.SimpleDateFormat
import java.util.*

val atletasUso = AtletasUso()
val deportesUso = DeportesUso()
val formatoFecha = SimpleDateFormat("dd/MM/yyyy")

fun main() {
    var opcion: Int = -1
    while (opcion != 0) {
        println("\t\t\t Menu")
        println("\t  Ingresa a un registro")
        println("\t1.- Atletas")
        println("\t2.- Deportes")
        println("\t0.- Salir\n")
        opcion = readLine()?.toIntOrNull() ?: 0
        when (opcion) {
            0 -> {
                println("Que tenga un buen día")

            }
            1 -> {
                menuAtletas()
            }
            2 -> {
                menuDeportes()
            }
            else -> {
                println("Ingrese una opcion correcta")
            }
        }
    }
}

fun menuAtletas() {
    var opcionMenu = -1
    while (opcionMenu!=0||opcionMenu!=5){
        println("\t\t\t Menu")
        println("\t1.- Ver Atletas")
        println("\t2.- Agregar Atleta")
        println("\t3.- Actualizar Registro")
        println("\t4.- Eliminar Atleta por nombre\n")
        println("\t5.- Regresar\n")
        println("\t0.- Salir\n")
        opcionMenu = readLine()?.toIntOrNull() ?: 0
        println(opcionMenu)

        when (opcionMenu) {
            0 -> {
                println("Que tenga un buen día")
            }
            1 -> {
                atletasUso.obtenerAtletas().forEach {
                    println(it.toString())
                }
            }
            2 -> {
                print("Nombre: ")
                val nombre: String = readLine() ?: ""

                print("Está habilitado para participar? (S/N)")
                val temp = readLine()
                val habilitado: Boolean = temp.equals("S", ignoreCase = true)

                print("Altura: ")
                val altura = readLine()?.toFloatOrNull() ?: 0.0f

                print("Fecha de nacimiento: (dd/MM/yyyy)")
                val fechaNacimiento = readLine() ?: ""

                print("Edad: ")
                val edad = readLine()?.toIntOrNull() ?: 0

                atletasUso.agregarAtleta(
                    Atleta(
                        nombre,
                        habilitado,
                        altura,
                        formatoFecha.parse(fechaNacimiento),
                        edad
                    )
                )
            }
            3 -> {
                print("Elija un atleta: (nombre):")
                val nombre: String = readLine() ?: ""
                print("Está habilitado para participar? (S/N)")
                val temp = readLine()
                val habilitado: Boolean = temp.equals("S", ignoreCase = true)

                print("Altura: ")
                val altura = readLine()?.toFloatOrNull() ?: 0.0f

                print("Fecha de nacimiento: ")
                val fechaNacimiento = readLine() ?: ""

                print("Edad: ")
                val edad = readLine()?.toIntOrNull() ?: 0

                atletasUso.actualizarAtleta(
                    nombre,
                    Atleta(
                        nombre,
                        habilitado,
                        altura,
                        formatoFecha.parse(fechaNacimiento),
                        edad
                    )
                )
            }
            4 -> {
                print("Atleta a eliminar (nombre): ")
                atletasUso.eliminarAtletaPorNombre(readLine() ?: "")
            }
            5 -> {
                return main()
            }
            else -> {
                println("Ingrese una opcion valida")
            }
        }
    }

}

fun menuDeportes() {
    var opcion = -1
    while(opcion!=0||opcion!=7){
        println("\t\t Menu")
        println("\t 1.- Ver Deportes")
        println("\t 2.- Agregar Deporte")
        println("\t 3.- Actualizar Deporte")
        println("\t 4.- Eliminar Deporte por nombre")
        println("\t 5.- Agregar un atleta")
        println("\t 6.- Eliminar un atleta")
        println("\t 7.- Regresar")
        println("\t 0.- Salir\n")
        opcion = readLine()?.toIntOrNull() ?: 0

        when (opcion) {
            0 -> {
                println("Que tenga un buen día")

            }
            1 -> {
                deportesUso.obtenerDeportes().forEach {
                    println(it.toString())
                }
            }

            2 -> {
                print("Nombre: ")
                val nombre: String = readLine() ?: ""

                print("Deporte puede ser individual? (S/N)")
                val temp = readLine()
                val individual: Boolean = temp.equals("S", ignoreCase = true)

                print("Tiempo promedio de un partido en minutos: ")
                val tiempoPromedioPartido = readLine()?.toFloatOrNull() ?: 0.0f

                print("Fecha registrada como deporte olimpico: (dd/MM/yyyy)")
                val fecharegistro = readLine() ?: ""

                print("Número de jugadores en un equipo: ")
                val numeroJugadores = readLine()?.toIntOrNull() ?: 0

                deportesUso.agregarDeporte(
                    Deporte(
                        nombre, individual, tiempoPromedioPartido, formatoFecha.parse(fecharegistro), numeroJugadores
                    )
                )
            }

            3 -> {
                print("Deporte a actualizar: (nombre)")
                val nombre: String = readLine() ?: ""
                print("Deporte individual?: (S/N)")
                val temp = readLine()
                val individual: Boolean = temp.equals("S", ignoreCase = true)

                print("Tiempo promedio de un partido en minutos: ")
                val tiempoPromedioPartido = readLine()?.toFloatOrNull() ?: 0.0f

                print("Fecha registrada como deporte olimpico: (dd/MM/yyyy)")
                val fechaRegistro = readLine() ?: ""

                print("Número de jugadores en un equipo: ")
                val numeroJugadores = readLine()?.toIntOrNull() ?: 0

                deportesUso.actualizarDeporte(
                    nombre,
                    Deporte(
                        nombre, individual, tiempoPromedioPartido, formatoFecha.parse(fechaRegistro), numeroJugadores
                    )
                )
            }

            4 -> {
                print("Deporte a eliminar: ")
                deportesUso.eliminarDeportePorNombre(readLine() ?: "")
            }

            5 -> {
                println("Ingresar nombre del deporte:")
                var deporte: String = readLine() ?: ""
                println("Ingresar nombre del atleta:")
                var atleta: String = readLine() ?: ""
                deportesUso.agregarAtleta(deporte, atleta)
            }

            6 -> {
                println("Ingresar nombre del deporte:")
                var deporte: String = readLine() ?: ""
                println("Ingresar nombre del atleta:")
                var atleta: String = readLine() ?: ""
                deportesUso.eliminarAtleta(deporte, atleta)
            }

            7 -> {
                return main()
            }
            else -> {
                println("Ingrese una opcion valida")
            }
        }
    }
}
