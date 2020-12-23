interface IPane<T> {
    fun getRows(): List<List<T>>
    fun getRow(id: Int): List<T>
    fun findRow(char: Char): Int
    fun contains(char: Char): Boolean
}