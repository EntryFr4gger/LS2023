package pt.isel.ls.tasks.db.modules.boards

import pt.isel.ls.tasks.db.errors.NotFoundException
import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.domain.Board
import pt.isel.ls.tasks.domain.User
import pt.isel.ls.tasks.domain.List as _List

/**
 * Board Management
 * */
interface BoardsDB {

    /**
     * Creates a new board.
     *
     * @param conn connection to a database.
     * @param name unique name for the board.
     * @param description board description.
     *
     * @return board unique identifier.
     * */
    fun createNewBoard(conn: TransactionManager, name: String, description: String): Int

    /**
     * Add a user to the board.
     *
     * @param conn connection to a database.
     * @param userId user unique identifier.
     * @param boardId board unique identifier.
     *
     * @return
     * */
    fun addUserToBoard(conn: TransactionManager, userId: Int, boardId: Int): Boolean

    /**
     * Get the detailed information of a board.
     *
     * @param conn connection to a database.
     * @param boardId board unique identifier.
     *
     * @return a Board.
     *
     * @throws NotFoundException couldn't get Board Details
     * */
    fun getBoardDetails(conn: TransactionManager, boardId: Int): Board

    /**
     * Get the lists of a board.
     *
     * @param conn connection to a database.
     * @param boardId board unique identifier.
     *
     * @return list of lists in a board.
     * */
    fun getAllLists(conn: TransactionManager, boardId: Int, skip: Int, limit: Int): List<_List>

    /**
     * Get the list with the users of a board.
     *
     * @param conn connection to a database.
     * @param boardId board unique identifier.
     *
     * @return list of Users in a board.
     * */
    fun getBoardUsers(conn: TransactionManager, boardId: Int, skip: Int, limit: Int): List<User>

    /**
     * Verify if the name is new
     *
     * @param conn connection to a database.
     * @param name unique name for the board.
     *
     * @return true if exists or false if it doesn't exist.
     * */
    fun hasBoardName(conn: TransactionManager, name: String): Boolean

    /**
     * Verify if the board exists.
     *
     * @param conn connection to a database.
     * @param boardId board unique identifier.
     *
     * @return true if exists or false if it doesn't exist.
     */
    fun hasBoard(conn: TransactionManager, boardId: Int): Boolean
}
