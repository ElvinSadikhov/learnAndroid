abstract class Student {
    init {
        println("it is the init section of Student abstract class")
    }

    // will be visible in child classes but cannot be accessed from any other classes
    protected var num = 1;

    abstract var abstrNum :Int;

    fun answer(){
        print("non abstract method \"answer\"")
    }

    abstract fun abstrMethod(a:Int);
}