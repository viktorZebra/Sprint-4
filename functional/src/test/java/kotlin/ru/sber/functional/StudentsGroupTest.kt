package ru.sber.functional

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class StudentsGroupTest {

    private val studentsGroup = StudentsGroup()
    private var studentsList = ArrayList<Student>()

    @BeforeEach
    fun setUp() {
        studentsList.addAll(
            listOf(
                Student("Артем", "Артемов", "null", 18,
                    averageRate = 5.0, "null", "null", null),

                Student("Олег", "Олегов", "null", 19,
                    averageRate = 4.1, "Saratov", "another", null),

                Student("Дмитрий", "Дмитриев", "null", 20,
                    averageRate = 3.2, "null", "null", null),

                Student("Иван", "Иванов", "null", 21,
                    averageRate = 2.8, "null", "null", null)
            )
        )

        studentsGroup.students = studentsList
    }

    @ParameterizedTest
    @MethodSource("predicate different average rate")
    fun `filter by average rate`(predicate: (Student) -> Boolean, expected: Int) {
        val filteredListStudent = studentsGroup.filterByPredicate(predicate)

        assertEquals(expected, filteredListStudent.size)
    }

    @ParameterizedTest
    @MethodSource("predicate different age")
    fun `filter by age`(predicate: (Student) -> Boolean, expected: Int) {
        val filteredListStudent = studentsGroup.filterByPredicate(predicate)

        assertEquals(expected, filteredListStudent.size)
    }

    companion object {
        @JvmStatic
        fun `predicate different average rate`() = listOf(
            Arguments.of( { student: Student -> student.averageRate >= 5.0 }, 1),
            Arguments.of( { student: Student -> student.averageRate == 0.0 }, 0),
            Arguments.of( { student: Student -> student.averageRate in 2.0..4.0 }, 2)
        )

        @JvmStatic
        fun `predicate different age`() = listOf(
            Arguments.of( { student: Student -> student.age > 18 }, 3),
            Arguments.of( { student: Student -> student.age == 21 }, 1)
        )
    }
}