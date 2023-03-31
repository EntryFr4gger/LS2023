package pt.isel.ls.tasks.domain

/**
 * Represents the Boards table in the database.
 *
 * @property id user unique identifier.
 * @property name the user's name.
 * @property email the user's unique email.
 * */
data class User(
    val id: Int,
    val name: String,
    val email: String
) {
    companion object {
        private val nameLength = 2..60
        private val emailLength = 6..320
        private const val EMAIL_REGEX = "^[A-Za-z\\d+_.-]+@(.+)$"

        fun isValidName(name: String) = name.length in nameLength

        fun isValidEmail(email: String) =
            email.length in emailLength && email.matches(Regex(EMAIL_REGEX))
    }
}
