package pt.isel.ls.tasks.db.modules.users

import pt.isel.ls.tasks.db.errors.NotFoundException
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
     * @param conn connection to a database.
     * @param name the user's name.
     * @param email the user's unique email.
     *
     * @return user's unique identifier.
     * */
    fun createNewUser(conn: TransactionManager, name: String, email: String): Int

    /**
     * Get the details of a user.
     *
     * @param conn connection to a database.
     * @param userId user unique identifier.
     *
     * @return a User.
     *
     * @throws NotFoundException couldn't get User
     * */
    fun getUserDetails(conn: TransactionManager, userId: Int): User

    /**
     * Get the list with all user available boards.
     *
     * @param conn connection to a database.
     * @param userId user unique identifier.
     *
     * @return list with user boards.
     * */
    fun getUserBoards(conn: TransactionManager, skip: Int, limit: Int, userId: Int): List<Board>

    /**
     * Verify if email is new
     *
     * @param conn connection to a database.
     * @param email the user's unique email.
     *
     * @return true if exists or false if it does not exist.
     * */
    fun hasUserEmail(conn: TransactionManager, email: String): Boolean

    /**
     * Verify if user exists.
     *
     * @param conn connection to a database.
     * @param userId user unique identifier.
     *
     * @return true if exists or false if it does not exist.
     */
    fun hasUser(conn: TransactionManager, userId: Int): Boolean

    /**
     * Verify if user exists in board.
     *
     * @param conn connection to a database.
     * @param userId user unique identifier.
     *
     * @return true if exists or false if it does not exist.
     */
    fun hasUserInBoard(conn: TransactionManager, userId: Int): Boolean

    /**
     * Validates the resquest premissions to access a board.
     *
     * @param conn connection to a database.
     * @param boardId board unique identifier.
     * @param requestId request user unique identifier.
     *
     * @return true if is valide or false if not.
     */
    fun validateResquestBoard(conn: TransactionManager, boardId: Int, requestId: Int): Boolean

    /**
     * Validates the resquest premissions to access a card.
     *
     * @param conn connection to a database.
     * @param cardId card unique identifier.
     * @param requestId request user unique identifier.
     *
     * @return true if is valide or false if not.
     */
    fun validateResquestCard(conn: TransactionManager, cardId: Int, requestId: Int): Boolean

    /**
     * Validates the resquest premissions to access a list.
     *
     * @param conn connection to a database.
     * @param listId list unique identifier.
     * @param requestId request user unique identifier.
     *
     * @return true if is valide or false if not.
     */
    fun validateResquestList(conn: TransactionManager, listId: Int, requestId: Int): Boolean
}
