package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "null",
    val age: Int = 0,
    val averageRate: Double,
    val city: String = "null",
    val specialization: String = "null",
    val prevEducation: String? = null,
)
