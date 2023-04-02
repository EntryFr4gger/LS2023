package pt.isel.ls.tasks.services.modules.token

import pt.isel.ls.tasks.db.TaskData
import pt.isel.ls.tasks.domain.stripBearer
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
     *
     * @throws ServicesError.InvalidArgumentException token isn't correct.
     * */
    fun getUserId(token: String): Int {
        isValidBearerToken(token)

        val cleanToken = token.stripBearer()
        return source.run { conn ->
            source.tokens.getUserID(conn, cleanToken)
        }
    }
}
