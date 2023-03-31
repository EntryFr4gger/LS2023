package pt.isel.ls.tasks.services.modules.tokenHandeler

import pt.isel.ls.tasks.db.TaskData
import pt.isel.ls.tasks.services.utils.isValidToken

/**
 * Token Services.
 * */
class TokenServices(val source: TaskData) {

    /**
     * Get the id of a user.
     *
     * @param token user token.
     *
     * @return a User id.
     * */
    fun getUserId(token: String): Int {
        isValidToken(token)

        val tokenCode = token.substring(7)
        return source.run { conn ->
            source.tokens.getUserID(conn, tokenCode)
        }
    }

    /**
     * Verify if token exists.
     *
     * @param token user unique token.
     *
     * @return true if exists or false if it does not exist.
     */
    fun validateToken(token: String): Boolean {
        isValidToken(token)

        val tokenCode = token.substring(7)
        return source.run { conn ->
            source.tokens.hasToken(conn, tokenCode)
        }
    }
}
