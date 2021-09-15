import com.sun.glass.ui.Size

abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val regex = "(7|8)\\d+".toRegex()

        if (value == null)
            return listOf(ErrorCode.NULL_VALUE)

        if (value.length > SizeConst.PHONE_SIZE.value)
            return listOf(ErrorCode.INVALID_SIZE_PHONE)

        if (!value.matches(regex))
            return listOf(ErrorCode.INVALID_CHARACTER_PHONE)

        return listOf()
    }
}

class NameValidator : Validator<String>(){
    override fun validate(value: String?): List<ErrorCode> {
        val regex = "[А-Я][а-я]+".toRegex()

        if (value == null)
            return listOf(ErrorCode.NULL_VALUE)

        if (value.length > SizeConst.USERNAME_SIZE.value)
            return listOf(ErrorCode.INVALID_SIZE_USERNAME)

        if (!value.matches(regex))
            return listOf(ErrorCode.INVALID_CHARACTER_USERNAME)

        return listOf()
    }
}

class MailValidator : Validator<String>(){
    override fun validate(value: String?): List<ErrorCode> {
        val regex = "[A-z]+@[A-z]+\\.[A-z]+".toRegex()

        if (value == null)
            return return listOf(ErrorCode.NULL_VALUE)

        if (!value.matches(regex))
            return listOf(ErrorCode.INVALID_CHARACTER_MAIL)

        if (value.length > SizeConst.MAIL_SIZE.value)
            return listOf(ErrorCode.INVALID_SIZE_MAIL)

        return listOf()
    }
}

class SnilsValidator : Validator<String>(){
    override fun validate(value: String?): List<ErrorCode> {
        val regex = "[0-9]+".toRegex()

        if (value == null)
            return listOf(ErrorCode.NULL_VALUE)

        if (!value.matches(regex))
            return listOf(ErrorCode.INVALID_CHARACTER_SNILS)

        if (value.length > SizeConst.SNILS_SIZE.value)
            return listOf(ErrorCode.INVALID_SIZE_SNILS)

        if (GetCheckNumber(value) != value.substring(9, 11).toInt())
            return listOf(ErrorCode.INVALID_CHECK_NUMBER_SNILS)

        return listOf()
    }

    private fun GetCheckNumber(value: String): Int{

        var checkNumber = 0
        for (i in 0..8){
            checkNumber += value[i].toString().toInt() * (9 - i)
        }

        if (checkNumber == 100 || checkNumber == 101)
            checkNumber = 0

        if (checkNumber > 101)
            checkNumber %= 101

        return checkNumber
    }
}
