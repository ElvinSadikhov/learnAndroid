import java.util.*

fun type(variable: Any): String{
    return when (variable){

        // 8 data types of Kotlin
        is Byte -> "Byte"
        is Short -> "Short"
        is Int -> "Int"
        is Long -> "Long"
        is Float -> "Float"
        is Double -> "Double"
        is String -> "String"
        is Char -> "Char"

        // something like "default" int Java and C
        else -> "INVALID"
    }
}

/*
//INPUT-OUTPUT
import java.util.*

fun main(args: Array<String>) {
    println("hi, what is your name?")
    val name = readLine()

    print("age:")
    val user = Scanner(System.`in`)
    val age = user.nextInt()

    print("his name is ${name}\tage = $age")
}*/


/*
//DATA TYPES and CONVERSIONS
fun main() {
    var age: Int
    age = 14
    var ageD = age.toDouble()
    println("age:$ageD\n")

    // any number is set to be Int by default!
    var num = 11
    println("num ($num) is ${type(num)}, and its range is -2^31..2^(31)-1, and it is preferable type in Kotlin")

    // any float point like number are set to be Double by default!
    var flnum = 11.2
    println("flnum($flnum) is ${type(flnum)}, and its precision after point is smaller that that of Double, and it is preferable type in Kotlin")

    // we can simple add f(or F) to the end of fl number in order to set him to be Float
    var numF = 12.3f // or var numF: Float = 12.3
    println("numF(${numF}f) is ${type(numF)}, and its precision after point is smaller that that of Double")

    // byte numbers
    var numB: Byte = num.toByte()
    println("numB($numB) is ${type(numB)}, and its range is -128..127")

    // WE CAN'T CONVERT LARGER NUMBER TYPE TO SMALLER ONE, EXAMPLE:
    // var numSh: Short = num  //GIVES AND ERROR
    // in such cases we must use toShort()/toDouble()/toInt()/toLong() and etc.
    var numSh = num.toShort()   // or ...numSh: Short =...
    println("numSh($numSh) is ${type(numSh)}, and its range is -32768..32767")

    // WE CAN'T CONVERT SMALLER NUMBER TYPE TO LARGER ONE, EXAMPLE:
    // var numL: Long = num //GIVES AN ERROR
    var numL: Long = num.toLong()
    // or we can put L(not small 'l') in order to set it to be Long
    var numL2= 12L
    println("numL2(${numL2}L) is ${type(numL2)}, and its range is -2^63..2^(63)-1")

    // everything between double quotas ( "everything xD" ) is set to be String by default
    var str = "hello"   // or var str: String = "Hello"
    println("str(\"$str\") is ${type(str)}")

    // Char value is set by default if we use ONE CHARACTER inside single quotas ( 'c' )
    var ch = 'c'    // or car ch: Char = 'c'
    println("ch('$ch') is ${type(ch)}")


    // another way of data conversion(for numbers) is to set a data type named "Number"
    var number: Number
    number = 12
    println("\nhere number($number) is ${type(number)}")

    number = 12.2
    println("here number($number) is ${type(number)}")

    number = 12.toShort()
    println("here number($number) is ${type(number)}")
}*/


/*

//STRING
fun main() {
    // creating a string:
    var string = "Hello"   //or var str: String = "Hello"
    string += " world" //concatenation
    println("string = \"$string\"\n")

    //another way of creating a string is creating an empty object of String class
    var str = String()
    str = "hi again"
    str += '!'  //can add a Char, too!
    println("str = \"$str\"")

    //length function returns the length of a String
    println("str.length() => ${str.length}")

    //get function returns the Char at given index
    println("str.get(1) => '${str.get(1)}' (which is ${type(str.get(1))})")
    //as String is an Array is Chars we can use array index to get a char at given index
    println("str[1] => '${str[1]}' (which is ${type(str.get(1))})")

    //subSequence function takes startIndex and endIndex and returns a part of a string (from startIndex to endIndex, excluding the endIndex itself)
    println("str.subSequence(3,7) => \"${str.subSequence(3,7)}\"")
    //it can also take a range, where it will return String INCLUDING(!) the last index (3..7) => 3,4,5,6,7
    println("str.subSequence(3..7) => \"${str.subSequence(3..7)}\"")

    //String comparing using compareTo function which can take a string and Boolean value for IgnoreCase (optional!)
    // return 0, if equal, negative number if a>b and positive if a<b
    println("str.compareTo(\"Hi agAIN!\",true) => ${str.compareTo("Hi agAIN!",true)}")
    println("str.compareTo(\"Hi agAIN!\") => ${str.compareTo("Hi agAIN!")}")
    println("str.compareTo(\"z\") => ${str.compareTo("z")}")

    //String concatenation using plus function (takes Any value (cuz converts it to String))
    println("str.plus(123) => \"${str.plus(123)}\"")

    //toUpperCase (or uppercase) toLowerCase (or lowercase) works the same as in C
    println("str.uppercase() *=> \"${str.uppercase()}\"")
    println("str.toLowerCase() *=> \"${str.toLowerCase()}\"")
}
*/


/*
//LOOPS
fun main() {
    //FOR-LOOP
    //there are different types of range:
    for(i in 1..4)  println("$i")
    for(i in 4 downTo 1)    println("$i")
    for(i in 1..4 step 2)   println("$i")
    for(i in 4 downTo 1 step 2) println("$i")

    //WHILE-LOOP
    //all the same as in Java and C
    var n = 1
    while(n++<2){ //same as n<2......n++ at the end
        println("lol")
    }

    //DO-WHILE-LOOP
    //all the same (first block will be executed even if condition is false)
    do{
        println("do-while-loop")
    } while(false);
}*/


/*
//BREAK and CONTINUE (labeled)
fun main() {
    //break and continue works for INNER(current) loop in Kotlin

    //labeled break is used for escaping the needed loop:
    var x = 1
    var y = 1
    myTag@ while (x<3){
        println("hi x")
        while (y<2){
            println("hi y")
            if (y==1)
                break@myTag // this break will break "x<3" loop and go to line 232
        }
    }// break will get us here)
    //another example:
    first@ for(i in 1..3){
        second@ for(j in 2..4){
            break@first
        }
    }// break will get us here

    //labeled continue works in THE SAME way!
    tag@ while (x<3){ // continue will get us here (meaningless code but anyway)
        println("hi x")
        while (y<2){
            println("hi y")
            if (y==1)
                continue@tag
        }
    }
}
*/


/*
//WHEN
fun main(){
    //when is switch (in Java, C)

    // we can use when as an expression:
    var num = 4
    var typedVersion = when (num){
        1 -> "One"
        2 -> "Two"
        3 -> "Three"
        4 -> "Four"
        else -> "Not in range 1..4"
    }
    println("$num => $typedVersion")

    //it can be used without expression
    when(num){
        1 -> println("it is one")
        2 -> ("it is two")
        3 -> println("it is three")
        4 -> println("it is four")
        else -> println("it is not in range 1..4")
    }

    //we can also write multiple statements using brackets
    when (num){
        1 -> {
            print("it is ")
            println("one!")
        }
        2 -> ("it is two")
        3 -> println("it is three")
        4 -> println("it is four")
        else -> println("it is not in range 1..4")
    }

    //multiple conditions are also available
    when(num){
        1,2,3,4 -> println("in range 1..4")
        else -> println("not in range 1..4")
    }

    //we can use "in" and "is" here too
    when(num){
        in 1..2 -> println("less than 3")
        is Int -> println("it is a Integer")
    }
}
*/


/*
//FUNCTIONS and RECURSION (tailed)
fun main() {
    println("${myFun(1,2.4f)}")
    unitFun()
}

// types of arguments MUST BE written (num1: Int etc.)
// type of return value MUST BE written (myFun(...): Double),
fun myFun(num1: Int, num2: Float): Double{
    return (num1.toFloat()+num2).toDouble()
}
// EXCEPTION => no return value(here we can skip writing :Unit
fun unitFun(){ //   same as "fun unitFun(): Unit {...}"
    println("unit fun")
    return // we can write empty return statement if we wish
}

//TAIL RECURSION
// the main difference here is improved mechanism when we use "tailrec" keyword before "fun"
// tail recursion works only when it is THE LAST CALL in the function
// the following code will not work with tailrec:
fun recursiveFactorial(n: Long) : Long {
    return if (n <= 1) {
        n
    } else {
        n * recursiveFactorial(n - 1) // as the last call of this function is " return n + "result" "
    }
}
// the algorithm of above code:
//1.If n is <= 1 then return n
//2.Calculate accum = recursiveFactorial(n – 1)
//3.return n * accum (LAST CALL)

//the CORRECT WAY of using tailrec is:
tailrec fun factorial(n: Long, accum: Long = 1): Long {
    val soFar = n * accum
    return if (n <= 1) {
        soFar
    } else {
        factorial(n - 1, soFar) //LAST CALL is the function ITSELF
    }
}
// the algorithm of above code:
//1.Calculate soFar = n * accum
//2.If n <= 1 then return soFar
//3.Call the factorial function, passing in n – 1 and soFar  (LAST CALL)

// the compiler will understand it as:(Java code)
//public final long factorial(long n, long accum) {
//    while(n > (long) 1) {
//        long var10000 = n - (long)1;
//        accum = n * accum;
//        n = var10000;
//    }
//
//    return n * accum;
//}
*/








