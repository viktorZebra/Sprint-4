class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER_USERNAME(-1, "Недопустимый символ"),
    INVALID_SIZE_USERNAME(-2, "Максимальная длина 16 символов"),
    INVALID_SIZE_PHONE(-3, "Максимальная длина 11 символов"),
    INVALID_CHARACTER_PHONE(-4, "Начинается с 7 или 8, только цифры"),
    INVALID_CHARACTER_MAIL(-5,"Только буквы латиницы и с доменом: user@имя_домена "),
    INVALID_SIZE_MAIL(-6, "Максимальная длина 32 символа"),
    INVALID_CHARACTER_SNILS(-7, "Только цифры"),
    INVALID_SIZE_SNILS(-8, "Максимальная длина 11 символов"),
    INVALID_CHECK_NUMBER_SNILS(-9,"Неверное контрольное число"),
    NULL_VALUE(-10, "Нет значения")
}

enum class SizeConst(val value: Int)
{
    USERNAME_SIZE(16),
    PHONE_SIZE(11),
    MAIL_SIZE(32),
    SNILS_SIZE(11)
}