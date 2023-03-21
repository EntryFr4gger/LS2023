package pt.isel.ls.tasks.db.modules.boards

import pt.isel.ls.tasks.domain.Board
import java.sql.Connection

class BoardsDataPostgres(): BoardsDB {
    override fun createNewBoard(conn: Connection?, name: String, description: String): Int {
        TODO("Not yet implemented")
    }

    override fun addUserToBoard(conn: Connection?, userId: Int, boardId: Int): Int {
        TODO("Not yet implemented")
    }

    override fun get(conn: String, name: Connection?, description: String): Int {
        TODO("Not yet implemented")
    }

    override fun getBoardDetails(conn: Connection?, boardId: Int): Board {
        TODO("Not yet implemented")
    }


}