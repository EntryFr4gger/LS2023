package pt.isel.ls.tasks.services.modules.tokenHandeler

import pt.isel.ls.tasks.db.TaskData
import pt.isel.ls.tasks.services.utils.*

class TokenServices(val source: TaskData) {

    /**
     * Trades the token for its ID
     */
    fun getId(token: String): Int {
        isValidToken(token)

        val tokenCode = token.substring(7)
        return source.run { conn ->
            source.tokens.getUserID(conn, tokenCode)
        }
    }

    fun validateToken(token: String): Boolean {
        isValidToken(token)

        val tokenCode = token.substring(7)
        return source.run { conn ->
            source.tokens.hasToken(conn, tokenCode)
        }
    }
}
