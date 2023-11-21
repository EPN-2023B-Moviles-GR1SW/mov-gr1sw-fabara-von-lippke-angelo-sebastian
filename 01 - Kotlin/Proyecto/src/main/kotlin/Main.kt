import java.util.*
import kotlin.collections.ArrayList

fun main() {
    println("Hello World!")
    //Inmutable
    val inmutable: String ="Angelo";
    //Mutable
    var mutable: String ="Fabara";

    var ejemploVariable = "Angelo Fabara "
    val edadEjemplo: Int = 12
    ejemploVariable.trim()

    val fechaNacimiento: Date = Date()
    //Switch
    val estadoCivilWhen ="C"
    when (estadoCivilWhen){
        ("C")->{
            println("Casado")
        }
        "S"->{
            println("Soltero")
        }
        else->{
            println("No sabemos")
        }
    }
    val coqueteo = if(estadoCivilWhen == "S")"Si" else "No"
    calcularSueldo(10.00)
    calcularSueldo(10.00,12.00)
    calcularSueldo(10.00,12.00,20.00)
    calcularSueldo(sueldo = 10.00, tasa = 12.00, bonoEspecial = 20.00)
    calcularSueldo(10.00, bonoEspecial =20.00)
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00)//parametros nombrados
    val sumaUno = Suma(1,1)
    val sumaDos = Suma(null,1)
    val sumaTres = Suma(1,null)
    val sumaCuatro = Suma(null,null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)

    val arregloEstatico: Array<Int> = arrayOf(1,2,3)
    println(arregloEstatico)

    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(
        1,2,3,4,5,6,7,8,9,10
        )
    println(arregloDinamico)

    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)
    val respuestaForEach: Unit= arregloDinamico
        .forEach {valorActual: Int->
            println("Valor actual:${valorActual}")
        }
    arregloDinamico.forEach { println(it) }
    arregloDinamico
        .forEachIndexed{indice:Int,valorActual:Int->
            println("valor ${valorActual} indice:${indice}")
        }
    //nuevo arreglo con valores modificados
    val respuestaMap: List<Double> =arregloDinamico
        .map{valorActual: Int ->
            return@map valorActual.toDouble()+100.00
        }
    println(respuestaMap)
    val respuestaMapDos =arregloDinamico.map{it+15}
    //nuevo arreglo filtrado (true or false)
    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual:Int->
            val mayoresACinco: Boolean = valorActual > 5
            return@filter mayoresACinco
        }
    val respuestaFilterDos = arregloDinamico.filter { it<=5 }
    println(respuestaFilter)
    println(respuestaFilterDos)

    //OR --> ANY(Alguno cumple?)
    //AND --> ALL(Todos cumplen?)
    val respuestaAny: Boolean = arregloDinamico
        .any{ valorActual:Int->
            return@any(valorActual>5)
        }
    println(respuestaAny)//true

    val respuestaAll: Boolean = arregloDinamico
        .all { valorActual:Int ->
            return@all(valorActual>5)
        }
    println(respuestaAll)//false

    val respuestaReduce: Int = arregloDinamico
        .reduce{
            acumulado: Int,valorActual:Int ->
            return@reduce(acumulado + valorActual)
        }
    println(respuestaReduce)//78
}
fun imprimirNombre(nombre: String){
    // template strings
    //Bienvenido: "+ nombre+ "____
    println("Nombre:${nombre}")
}
fun calcularSueldo(
    sueldo: Double,//Requerido
    tasa: Double = 12.00,//Opcional(defecto)
    bonoEspecial: Double? = null,//Opcion null ->nulable
):Double{
    //Int ->Int? (nullable)
    //String -> String?(nullable)
    //Date -> Date? (nullable)
    if(bonoEspecial==null){
        return sueldo*(100/tasa)
    }else{
        bonoEspecial.dec()
        return sueldo * (100/tasa)+ bonoEspecial
    }
}
abstract class Numeros(
    //uno: Int,//{parametro (sin modificador de acceso)}
    //private var uno: Int, //propiedad pública Clase numeros.uno
    //var uno Int,
    protected val numeroUno: Int,
    protected val numeroDos: Int,
){
    //var cedula: String = "" (public es por defecto)
    //private valorCalculado: Int=0(private)
    init{
        this.numeroUno;this.numeroDos;
        numeroUno;numeroDos;
        println("Inicializando")
    }

}
class Suma(   //Constructor Primario Suma
    uno: Int,   //Parametro
    dos: Int   //Parametro
): Numeros(uno,dos){//<- Constructor del Padre
    init{//Bloque constructor primario
        this.numeroUno;numeroUno;
        this.numeroDos;numeroDos;
    }
    constructor(
        uno:Int?,
        dos:Int
    ):this(
        if ( uno == null)0 else uno,
        dos,
    ){
        numeroUno;
    }

    constructor(
        uno:Int,
        dos:Int?
    ):this(
        uno,
        if(dos == null) 0 else uno,
    )

    constructor(
        uno: Int?,
        dos: Int?
    ):this(
        if(uno == null)0 else uno,
        if(dos == null)0 else dos
    )
    public fun sumar(): Int{
        val total =numeroUno+numeroDos
        agregarHistorial(total)
        return total
    }
    companion object {
        val pi= 3.14
        fun elevarAlCuadrado(num:Int):Int{
            return num*num
        }
        val historialSumas= arrayListOf<Int>()

        fun agregarHistorial(valorNuevaSuma:Int){
            historialSumas.add(valorNuevaSuma)
        }
    }
}

