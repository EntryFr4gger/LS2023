package pt.isel.ls.tasks.services.modules.tokenHandeler

import pt.isel.ls.tasks.db.TaskData
import pt.isel.ls.tasks.services.utils.ServicesUtils

/**
 * Token Services.
 * */
class TokenServices(source: TaskData) : ServicesUtils(source) {

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
}
