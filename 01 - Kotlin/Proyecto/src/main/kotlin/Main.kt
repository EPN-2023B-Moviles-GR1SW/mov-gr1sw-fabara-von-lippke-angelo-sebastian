import java.util.*

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
}