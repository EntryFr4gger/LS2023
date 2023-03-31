package pt.isel.ls.tasks.domain

/**
 * Represents the Boards table in the database.
 *
 * @property token user token.
 * @property userId user unique identifier.
 * */
class Token(val token: String, val userId: Int) {
    companion object {
        private val tokenLength = 10..50
        private val tokenRegex = "^Bearer [A-Za-z0-9\\-]+\$".toRegex()

        fun isValidToken(token: String) =
            token.length in tokenLength && token.matches(tokenRegex)
    }
}
