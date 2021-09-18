package ru.sber.functional

import kotlin.math.pow
import kotlin.reflect.typeOf

object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
     */
    fun buildPowFunction(pow: Int): (Int) -> Int = { value: Int ->
        value.toDouble().pow(pow).toInt()

    }
}
