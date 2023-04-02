package pt.isel.ls.tasks.services.modules.users

import pt.isel.ls.tasks.db.TaskData
import pt.isel.ls.tasks.domain.Board
import pt.isel.ls.tasks.domain.User
import pt.isel.ls.tasks.services.utils.ServicesUtils
import java.util.*

/**
 * User Services.
 * */
class UsersServices(source: TaskData) : ServicesUtils(source) {

    /**
     * Create a new user.
     *
     * @param name the user's name.
     * @param email the user's unique email.
     *
     * @return user's unique identifier and token.
     *
     * @throws ServicesError.InvalidArgumentException name is the worng length.
     * @throws ServicesError.InvalidArgumentException email is the worng length.
     * @throws ServicesError.AlreadyExistsException if email is in use.
     * */
    fun createNewUser(name: String, email: String): UserCreateResponse {
        isValidUserName(name)
        isValidUserEmail(email)

        return source.run { conn ->
            isUserNewEmail(conn, email)

            val userId = source.users.createNewUser(conn, name, email)
            val token = source.tokens.createNewToken(conn, UUID.randomUUID().toString(), userId)

            UserCreateResponse(userId, token)
        }
    }

    /**
     * Get the details of a user.
     *
     * @param userId user unique identifier.
     *
     * @return a User.
     *
     * @throws ServicesError.InvalidArgumentException if id isn't correct.
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
     *
     * @throws ServicesError.InvalidArgumentException if id isn't correct.
     * */
    fun getUserBoards(requestId: Int): List<Board> {
        isValidUserId(requestId)

        return source.run { conn ->
            source.users.getUserBoards(conn, requestId)
        }
    }
}
