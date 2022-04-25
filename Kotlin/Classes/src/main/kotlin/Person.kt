// we must make this class OPEN in order to make it be superclass!
// it is primary constructor
open class Person(private var personAge: Int, private var personName: String, val personId: Long  ) {
    // id will be val BY DEFAULT
    public var name: String
    var age: Int // public BY DEFAULT
    private val id: Long

    init {
        name = personName
        age = personAge
        id = personId
    }

    // we must make it open in order to let child classes to override it
    // protected access modifier give us a chance to use this fun in this class and in child classes as well
    protected open fun changeCase(str: String): String{
        return str.uppercase()
    }

    // will be public by default
    fun showInfo(){
        print("\nName: ${changeCase(name)}\nAge: $age\nAnd his ID is: $id")
    }
}

