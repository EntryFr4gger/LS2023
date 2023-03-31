package pt.isel.ls.tasks.db.modules.boards

import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.domain.Board
import pt.isel.ls.tasks.domain.List as _List

/**
 * Board Management
 * */
interface BoardsDB {

    /**
     * Creates a new board.
     *
     * @param conn connection to database.
     * @param name unique name for the board.
     * @param description board description.
     *
     * @return board unique identifier.
     * */
    fun createNewBoard(conn: TransactionManager, name: String, description: String): Int

    /**
     * Add a user to the board.
     *
     * @param conn connection to database.
     * @param userId user unique identifier.
     * @param boardId board unique identifier.
     *
     * @return
     * */
    fun addUserToBoard(conn: TransactionManager, userId: Int, boardId: Int): Int

    /**
     * Get the detailed information of a board.
     *
     * @param conn connection to database.
     * @param boardId board unique identifier.
     *
     * @return a Board.
     * */
    fun getBoardDetails(conn: TransactionManager, boardId: Int): Board

    /**
     * Get the lists of a board.
     *
     * @param conn connection to database.
     * @param boardId board unique identifier.
     *
     * @return list of lists of a board.
     * */
    fun getAllLists(conn: TransactionManager, boardId: Int): List<_List>

    /**
     * Verify if name is new
     *
     * @param conn connection to database.
     * @param name unique name for the board.
     *
     * @return true if exists or false if it does not exist.
     * */
    fun hasBoardName(conn: TransactionManager, name: String): Boolean

    /**
     * Verify if board exists.
     *
     * @param conn connection to database.
     * @param boardId board unique identifier.
     *
     * @return true if exists or false if it does not exist.
     */
    fun hasBoard(conn: TransactionManager, boardId: Int): Boolean
}
