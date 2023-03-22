package pt.isel.ls.tasks.db.modules.boards

import pt.isel.ls.tasks.domain.Board
import java.sql.Connection


/**
 *
 * */
interface BoardsDB {

    /**
     *
     * */
    fun createNewBoard(conn: Connection, name: String, description: String): Int

    /**
     *
     * @return board unique id???
     * */
    fun addUserToBoard(conn: Connection, userId: Int, boardId: Int): Int

    /**
     *
     * */
    fun getUserBoards(conn: Connection, userId: Int): List<Board>

    /**
     *
     * */
    fun getBoardDetails(conn: Connection, boardId: Int): Board

}