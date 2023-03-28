package pt.isel.ls.tasks.db.modules.boards

import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.domain.Board

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
     * Get the list with all user available boards.
     *
     * @param conn connection to database.
     * @param userId user unique identifier.
     *
     * @return list with user boards.
     * */
    fun getUserBoards(conn: TransactionManager, userId: Int): List<Board>

    /**
     * Get the detailed information of a board.
     *
     * @param conn connection to database.
     * @param boardId board unique identifier.
     *
     * @return a Board.
     * */
    fun getBoardDetails(conn: TransactionManager, boardId: Int): Board
}
