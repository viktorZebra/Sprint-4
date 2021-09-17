package ru.sber.functional

import kotlin.math.pow
import kotlin.reflect.typeOf

object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
     */
    fun buildPowFunction(pow: Int): (Any) -> Any = { value: Any ->
        when (value){
            is Int -> value.toDouble().pow(pow).toInt()
            is Double -> value.pow(pow)
            else -> "Type ERROR:\n Expected: (Int, Double)\n Actual: ${value::class.simpleName}\n"
        }

    }
}
