package maxbe.tvpasswordhelper

// TODO: Move to IPane? 
data class ThreeRowPane<T>(override val switchChar: T, val row1: List<T>, val row2: List<T>, val row3: List<T>) :
    IPane<T> {
    override fun getRows() = listOf(row1, row2, row3)
}

data class FourRowPane<T>(override val switchChar: T, val row1: List<T>, val row2: List<T>, val row3: List<T>, val row4: List<T>) :
    IPane<T> {
    override fun getRows() = listOf(row1, row2, row3, row4)
}

data class FiveRowPane<T>(override val switchChar: T, val row1: List<T>, val row2: List<T>, val row3: List<T>, val row4: List<T>, val row5: List<T>) :
    IPane<T> {
    override fun getRows() = listOf(row1, row2, row3, row4, row5)
}