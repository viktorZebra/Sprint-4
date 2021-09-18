package ru.sber.generics

import com.sun.org.apache.xpath.internal.operations.Bool
import java.util.*

// 1.
fun <T1, T2> compare(p1: Pair<T1, T2>, p2: Pair<T1, T2>): Boolean = p1 == p2

// 2.
// не знаю насколько уместно здесь учитывать числа с плавающей запятой и их неточность сравнения
fun <T: Comparable<T>>countGreaterThan(anArray: Array<T>, elem: T): Int = anArray.filter { it > elem }.size

// 3.
class Sorter<T: Comparable<T>> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        // binarySearch Returns:
        //the index of the element, if it is contained in the list within the specified range;
        // otherwise, the inverted insertion point (-insertion point - 1).

        val insertIndex = if (list.contains(value))
            list.binarySearch(value)
        else
            -list.binarySearch(value) - 1

        list.add(insertIndex, value)
    }
}

// 4.
class Stack<T> {
    private val stack: MutableList<T> = mutableListOf()

    fun push(value: T) = stack.add(0, value)

    fun pop(): T = stack.removeAt(0)

    fun isEmpty(): Boolean = stack.isEmpty()

    fun peek(): T = stack[0]

    fun size() = stack.size

    fun printStack() = stack.forEach { print("$it ") }
}