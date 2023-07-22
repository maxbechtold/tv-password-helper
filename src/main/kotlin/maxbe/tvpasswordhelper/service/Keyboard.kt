package maxbe.tvpasswordhelper.service

import maxbe.tvpasswordhelper.OnPaneDistanceCalculator

interface Keyboard {
    val inputComfort: InputComfort
    fun getCalculators(): List<OnPaneDistanceCalculator>

    fun isSwitch(char: Char): Boolean
    fun backwardSwitch(switch: Char): Char

}
