class College() : Student() {

    override fun abstrMethod(a: Int) {
        println("This overrided method \"abstrMethod\" in College class")
    }

    // need to be overridden OR we could write "override var abstrNum: Int" in primary constructor
    override var abstrNum = 1;

    // var num can be accessed as it is a member variable of super (abstract) class Student
    private val res: Int = 5 + num;

}