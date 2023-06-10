package pt.isel.ls.tasks.services.modules.users

import pt.isel.ls.tasks.db.TaskData
import pt.isel.ls.tasks.domain.Board
import pt.isel.ls.tasks.domain.User
import pt.isel.ls.tasks.services.errors.ServicesError
import pt.isel.ls.tasks.services.modules.users.responses.UserInfoResponse
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
     * @return new user unique identifier and token.
     * */
    fun createNewUser(name: String, email: String, password: String): UserInfoResponse {
        isValidUserName(name)
        isValidUserEmail(email)

        return source.run { conn ->
            isUserNewEmail(conn, email)

            val userId = source.users.createNewUser(conn, name, email, hashPassword(password))
            val token = source.tokens.createNewToken(conn, UUID.randomUUID().toString(), userId)

            UserInfoResponse(userId, token)
        }
    }

    /**
     * Login verifications.
     *
     * @param email the user's unique email.
     * @param password user's passaword
     *
     * @return new user unique identifier and token.
     * */
    fun loginUser(email: String, password: String): UserInfoResponse {
        isValidUserEmail(email)

        return source.run { conn ->
            val user = source.users.loginUserInfo(conn, email)

            if (user.password != hashPassword(password)) {
                throw ServicesError.AuthenticationException("Passwords dont match")
            }

            val token = source.tokens.getUserToken(conn, user.id)

            UserInfoResponse(user.id, token)
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
     * @param skip skip database tables.
     * @param limit limit database tables.
     *
     * @return list with user boards.
     * */
    fun getUserBoards(requestId: Int, skip: Int, limit: Int): List<Board> {
        isValidUserId(requestId)
        isValidSkipAndLimit(skip, limit)

        return source.run { conn ->
            source.users.getUserBoards(conn, skip, limit, requestId)
        }
    }

    /**
     * Gets all Users in the database.
     *
     * @param requestId request user unique identifier.
     *
     * @return list of Users.
     * */
    fun getAllUsers(boardId: Int, requestId: Int): List<User> {
        isValidUserId(requestId)

        return source.run { conn ->
            authorizationBoard(conn, boardId, requestId)

            source.users.getAllUsers(conn, boardId)
        }
    }
}
