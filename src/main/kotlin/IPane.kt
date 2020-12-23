interface IPane<T> {
    val switchChar: T

    fun getRows(): List<List<T>>
    fun getRow(id: Int) = getRows()[id]
    fun findRow(char: T) = getRows().indexOfFirst { it.contains(char) }
    fun contains(char: T) = findRow(char) != -1
}