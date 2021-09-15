import com.google.gson.Gson
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class ClientServiceTest {

    private val gson = Gson()
    private val clientService = ClientService()

    @ParameterizedTest
    @MethodSource("success save data set")
    fun `success save client`(sourcePath: String) {
        val client = getClientFromJson(sourcePath)
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    @ParameterizedTest
    @MethodSource("fail save client")
    fun `fail save client`(sourcePath: String, errorCode: ErrorCode) {
        val client = getClientFromJson(sourcePath)
        val exception = assertThrows<ValidationException> { clientService.saveClient(client) }

        assertEquals(exception.errorCode[0], errorCode)
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
            .takeIf { it != null }
            ?.let { gson.fromJson(it.readText(), Client::class.java) }
            ?: throw Exception("Что-то пошло не так))")

    companion object {
        @JvmStatic
        fun `success save data set`() = listOf(
                Arguments.of("/success/user.json")
        )

        @JvmStatic
        fun `fail save client`() = listOf(
                Arguments.of("/fail/user_bad_lastname.json", ErrorCode.INVALID_SIZE_USERNAME),
                Arguments.of("/fail/user_bad_firstname.json", ErrorCode.INVALID_CHARACTER_USERNAME),
                Arguments.of("/fail/snils_bad_size.json", ErrorCode.INVALID_SIZE_SNILS),
                Arguments.of("/fail/snils_bad_check_num.json", ErrorCode.INVALID_CHECK_NUMBER_SNILS),
                Arguments.of("/fail/snils_bad_char.json", ErrorCode.INVALID_CHARACTER_SNILS),
                Arguments.of("/fail/phone_bad_size.json", ErrorCode.INVALID_SIZE_PHONE),
                Arguments.of("/fail/phone_bad_char.json", ErrorCode.INVALID_CHARACTER_PHONE),
                Arguments.of("/fail/mail_bad_size.json", ErrorCode.INVALID_SIZE_MAIL),
                Arguments.of("/fail/mail_bad_char.json", ErrorCode.INVALID_CHARACTER_MAIL),
        )
    }
}