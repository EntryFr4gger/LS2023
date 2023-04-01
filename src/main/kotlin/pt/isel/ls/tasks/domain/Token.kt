package pt.isel.ls.tasks.domain

import pt.isel.ls.tasks.services.utils.isValidId

/**
 * Represents the Boards table in the database.
 *
 * @property token user token.
 * @property userId user unique identifier.
 * */
class Token(val token: String, val userId: Int) {
    companion object {
        private val tokenLength = 10..50
        private val tokenRegex = "[A-Za-z0-9\\-]+\$".toRegex()

        fun isValidToken(token: String) =
            token.length in tokenLength && token.matches(tokenRegex)
    }

    init {
        require(isValidToken(token)) { "Invalid token" }
        require(!isValidId(userId)) { "Invalid user id" }
    }
}
