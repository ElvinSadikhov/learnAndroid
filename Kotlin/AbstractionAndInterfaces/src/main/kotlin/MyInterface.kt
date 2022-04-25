interface MyInterface {

    // all properties(var/val) in interface ARE abstract and can't be assigned
    var num: Int // "var num: Int = 1" will give a compilation error
    var id: Long

    // interface may have both abstract and none-abstract methods
    // this method is none-abstract as it has function body
    fun hi(){
        println("hi")
    }
    // all methods in interface are abstract by defaut if there is no any function body
    // this fun is abstract as it has no body
    fun abstrMethod()


}