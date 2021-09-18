package ru.sber.functional

class StudentsGroup {

    private lateinit var students: List<Student>

    fun filterByPredicate(predicate: (Student) -> Boolean): List<Student> {
        if (this::students.isInitialized){
            return students.filter(predicate)
        }

        return emptyList()
    }

    fun initStudentsList(students: List<Student>)
    {
        this.students = students
    }
}
