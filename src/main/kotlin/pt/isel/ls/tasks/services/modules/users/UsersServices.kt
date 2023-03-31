package pt.isel.ls.tasks.services.modules.users

import pt.isel.ls.tasks.db.TaskData
import pt.isel.ls.tasks.domain.Board
import pt.isel.ls.tasks.domain.User
import pt.isel.ls.tasks.services.utils.ServicesUtilsDB
import pt.isel.ls.tasks.services.utils.isValidUserEmail
import pt.isel.ls.tasks.services.utils.isValidUserId
import pt.isel.ls.tasks.services.utils.isValidUserName
import java.util.UUID

class UsersServices(val source: TaskData) {
    private val utils = ServicesUtilsDB(source)

    fun createNewUser(name: String, email: String): Pair<Int, String> {
        isValidUserName(name)
        isValidUserEmail(email)

        return source.run { conn ->
            utils.isUserNewEmail(conn, email)

            val userID = source.users.createNewUser(conn, name, email)
            val token = source.tokens.createNewToken(conn, UUID.randomUUID().toString(), userID)

            Pair(userID, token)
        }
    }

    fun getUserDetails(userId: Int): User {
        isValidUserId(userId)

        return source.run { conn ->
            source.users.getUserDetails(conn, userId)
        }
    }


    fun getUserBoards(requestId: Int): List<Board> {
        isValidUserId(requestId)

        return source.run { conn ->
            source.users.getUserBoards(conn, requestId)
        }
    }
}
