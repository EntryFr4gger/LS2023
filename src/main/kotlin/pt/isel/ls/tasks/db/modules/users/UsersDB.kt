package pt.isel.ls.tasks.db.modules.users

import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.domain.User

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
    fun createNewUser(conn: TransactionManager, name: String, email: String): Int

    /**
     * Get the details of a user.
     *
     * @param conn connection to database.
     * @param userId user unique identifier.
     *
     * @return a User.
     * */
    fun getUserDetails(conn: TransactionManager, userId: Int): User

    /**
     *
     * */
    fun isNewEmail(conn: TransactionManager, email: String): Boolean

    /**
     *
     */
    fun hasUser(conn: TransactionManager, userId: Int): Boolean
}

