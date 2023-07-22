package maxbe.tvpasswordhelper.service

enum class Service {
    DisneyPlus,
    Netflix
    ;

    fun keyboard(): Keyboard {
        if (this == Netflix)
            return NetflixQwerty()
        if (this == DisneyPlus)
            return DisneyPlusQwerty()
        throw IllegalArgumentException()
    }

}