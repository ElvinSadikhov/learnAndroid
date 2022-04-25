class Father: Person(30,"Murad",10992212) {
    override fun changeCase(str: String): String {
        return str.lowercase()
    }
}