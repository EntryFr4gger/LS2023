package pt.isel.ls.tasks.services.modules.users

import pt.isel.ls.tasks.db.TaskData
import pt.isel.ls.tasks.domain.Board
import pt.isel.ls.tasks.domain.User
import pt.isel.ls.tasks.services.utils.ServicesUtils
import java.util.UUID

/**
 * User Services.
 * */
class UsersServices(source: TaskData): ServicesUtils(source) {

    /**
     * Create a new user.
     *
     * @param name the user's name.
     * @param email the user's unique email.
     *
     * @return user's unique identifier and token.
     * */
    fun createNewUser(name: String, email: String): Pair<Int, String> {
        isValidUserName(name)
        isValidUserEmail(email)

        return source.run { conn ->
            isUserNewEmail(conn, email)

            val userId = source.users.createNewUser(conn, name, email)
            val token = source.tokens.createNewToken(conn, UUID.randomUUID().toString(), userId)

            Pair(userId, token)
        }
    }

    /**
     * Get the details of a user.
     *
     * @param userId user unique identifier.
     *
     * @return a User.
     * */
    fun getUserDetails(userId: Int): User {
        isValidUserId(userId)

        return source.run { conn ->
            source.users.getUserDetails(conn, userId)
        }
    }

    /**
     * Get the list with all user available boards.
     *
     * @param requestId request user unique identifier.
     *
     * @return list with user boards.
     * */
    fun getUserBoards(requestId: Int): List<Board> {
        isValidUserId(requestId)

        return source.run { conn ->
            source.users.getUserBoards(conn, requestId)
        }
    }
}
