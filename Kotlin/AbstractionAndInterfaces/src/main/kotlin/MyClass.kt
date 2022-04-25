class MyClass: MyInterface {

    override var num: Int = 6

    override var id: Long = 0L
        get() = 5 // idk how it works

    override fun abstrMethod() {
        println("This is overridden fun \"abstrMethod\" in MyClass")
    }

    override fun hi() {
        super.hi()
    }




}