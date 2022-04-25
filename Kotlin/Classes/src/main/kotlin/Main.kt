fun main() {
    val obj: Person = Person(18, "Elvin", 110110112L)
    println(obj.name)
    println(obj.age)
//    obj.id // will give a compilation error as it is private
    println(obj.personId) // we can see it as we didn't make it private in primary constructor, and it became public BY DEFAULT

    obj.showInfo()
//    obj.toUpper()  // will give a compilation error as it is also private

    val obj2: Father = Father()
    obj2.showInfo()
}