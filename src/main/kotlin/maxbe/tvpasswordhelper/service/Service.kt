package maxbe.tvpasswordhelper.service

enum class Service {
    DisneyPlus,
    Netflix,
    ParamountPlus
    ;

    fun keyboard(): Keyboard {
        return when {
            this == Netflix -> NetflixQwerty()
            this == DisneyPlus -> DisneyPlusQwerty()
            this == ParamountPlus -> ParamountPlusQwerty()
            else -> throw IllegalArgumentException()
        }
    }

}