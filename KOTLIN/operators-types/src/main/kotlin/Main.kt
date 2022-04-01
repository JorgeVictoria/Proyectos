fun main(args: Array<String>) {

    //operaciones
    //suma
    println("1 + 1 = " + (1+1))

    //resta
    println("53 - 3 = " + (53-3))

    //division entera
    println("50 : 10 = " +  50/10)

    //division con decimales
    println("1.0 : 2.0 = " + 1.0/2.0)

    //producto
    println("2.0 * 3.5 = " + 2.0*3.5)

    //operaciones con diferentes tipos de numeros
    println()
    println("6 * 50 = " + 6*50)
    println("6.0 * 50.0 = " + 6.0*50.0)
    println("6.0 * 50 = " + 6.0*50)

    //metodos con numeros primitivos. Los maneja como objetos
    println()
    println(2.times(3))
    println(3.5.plus(4))
    println(2.4.div(2))

    //tipos de datos
    //casting de tipo de datos
    println()
    val i: Int = 6
    val b1 = i.toByte()
    println(b1)

    //asignar una variable a diferentes tipos
    println()
    val b2: Byte = 1
    println(b2)

    //cuando pasamos de un tipo de dato sin casting previo, da error
    //val i1 : Int = b2
    //val i2 : String = b2
    //val i3 : Double = b2

    //para evitar errores, hacemos casting, si es posible
    println()
    val i6 : Int = b2.toInt()
    val i7 : String = b2.toString()
    val i8 : Double = b2.toDouble()
    println("$i6, $i7, $i8")

    //variables con un valor largo. Se puede usar guion bajo
    println()
    val oneMillion = 1_000_000
    val socialSecurityNumber = 999_99_9999L
    val hexBytes = 0xFF_EC_DE_5E
    val bytes = 0b11010010_01101001_10010100_10010010
    println(oneMillion)

    //var vs val
    //uso de var
    println()
    var fish = 1
    fish = 2

    //uso de val
    val aquarium = 1
    //aquarium = 2 //el valor de la variable no se puede cambiar

    //Kotlin puede coger el tipo de dato de una variable, pero la podemos definir
    //no podremos cambiar el tipo de dato si definimos el tipo previamente
    var fishes: Int = 12
    var plants: Double = 2.5

    //Strings
    //plantilla de string
    println()
    println("I have $fishes fishes and ${plants.toInt()} plants")

    //plantilla de string con expresion
    println("I have ${fishes + plants.toInt()} elements between fishes and plants")
}