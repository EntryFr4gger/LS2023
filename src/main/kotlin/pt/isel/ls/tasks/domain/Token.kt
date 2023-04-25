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
        private const val uuidLength = 36
        private const val bearerLength = "Bearer ".length + uuidLength
        private val uuidRegex = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}".toRegex()
        private val tokenRegex =
            "Bearer [a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}".toRegex()

        fun isValidToken(token: String) =
            token.length == uuidLength && token.matches(uuidRegex)

        fun isValidBearerToken(token: String) =
            token.length == bearerLength && token.matches(tokenRegex)
    }

    init {
        require(isValidToken(token)) { "Invalid token" }
        require(!isValidId(userId)) { "Invalid user id" }
    }
}

fun String.stripBearer() = substring(7)
