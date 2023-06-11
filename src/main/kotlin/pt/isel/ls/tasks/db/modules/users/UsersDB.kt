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
     * @param password the user's hashed password.
     *
     * @return user's unique identifier.
     * */
    fun createNewUser(conn: TransactionManager, name: String, email: String, password: String): Int

    /**
     * Gets the login information.
     *
     * @param conn connection to a database.
     * @param email the user's unique email.
     *
     * @return a User.
     * */
    fun loginUserInfo(conn: TransactionManager, email: String): User

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
     * @param skip skip tables.
     * @param limit limits the return values.
     *
     * @return list with user boards.
     * */
    fun getUserBoards(conn: TransactionManager, skip: Int, limit: Int, userId: Int): List<Board>

    /**
     * Deletes a board.
     *
     * @param conn connection to a database.
     * @param boardId board unique identifier.
     *
     * @return true if it has deleted or false otherwise.
     * */
    fun deleteBoardUsers(conn: TransactionManager, boardId: Int)

    /**
     * Gets all Users that are not on the board.
     *
     * @param conn connection to a database.
     * @param boardId to remove users from board.
     *
     * @return list of Boards.
     * */
    fun getAllUsersNotInBoard(conn: TransactionManager, boardId: Int): List<User>

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
     * Verify if user was boards.
     *
     * @param conn connection to a database.
     * @param userId user unique identifier.
     *
     * @return true if exists or false if it does not exist.
     */
    fun hasUserBoards(conn: TransactionManager, userId: Int): Boolean

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
