package pt.isel.ls.tasks.db.modules.users

import pt.isel.ls.tasks.domain.User
import java.sql.Connection

/**
 * User Management.
 * */
interface UsersDB {

    /**
     * Create a new user.
     *
     * @param conn connection to database.
     * @param name the user's name.
     * @param email the user's unique email.
     *
     * @return user's unique identifier.
     * */
    fun createNewUser(conn: Connection, name: String, email: String): Int

    /**
     * Get the details of a user.
     *
     * @param conn connection to database.
     * @param userId user unique identifier.
     *
     * @return a User.
     * */
    fun getUserDetails(conn: Connection, userId: Int): User

}