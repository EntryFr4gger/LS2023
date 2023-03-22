package pt.isel.ls.tasks.db.modules.users

import pt.isel.ls.tasks.domain.User
import java.sql.Connection

/**
 *
 * */
interface UsersDB {

    /**
     *
     * */
    fun createNewUser(conn: Connection, name: String, email: String): Int

    /**
     *
     * */
    fun getUserDetails(conn: Connection, userId: Int): User

}