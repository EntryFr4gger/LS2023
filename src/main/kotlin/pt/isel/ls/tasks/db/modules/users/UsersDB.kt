package pt.isel.ls.tasks.db.modules.users

import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.domain.Board
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
     * Get the list with all user available boards.
     *
     * @param conn connection to database.
     * @param userId user unique identifier.
     *
     * @return list with user boards.
     * */
    fun getUserBoards(conn: TransactionManager, userId: Int): List<Board>

    /**
     * Verify if email is new
     *
     * @param conn connection to database.
     * @param email the user's unique email.
     *
     * @return true if exists or false if it does not exist.
     * */
    fun isNewEmail(conn: TransactionManager, email: String): Boolean

    /**
     * Verify if user exists.
     *
     * @param conn connection to database.
     * @param userId user unique identifier.
     *
     * @return true if exists or false if it does not exist.
     */
    fun hasUser(conn: TransactionManager, userId: Int): Boolean

    /**
     *
     */
    fun hasUserInBoard(conn: TransactionManager, userId: Int): Boolean
}
