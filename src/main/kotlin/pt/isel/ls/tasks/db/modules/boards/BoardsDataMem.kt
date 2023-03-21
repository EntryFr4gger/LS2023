package pt.isel.ls.tasks.db.modules.boards

import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.model.Board
import java.lang.Error
import java.sql.Connection

class BoardsDataMem(private val source: TasksDataStorage): BoardsDB {

    override fun createNewBoard(conn: Connection?, name: String, description: String): Int {
        val id = source.nextBoardId.getAndIncrement()

        if(source.boards.values.any { it.name == name })
            throw error("Name in use")

        source.boards[id] = Board(id, name, description)
        return id
    }

    override fun addUserToBoard(conn: Connection?, userId: Int, boardId: Int): Int {
        TODO("Not yet implemented")
    }

    override fun get(conn: String, name: Connection?, description: String): Int {
        TODO("Not yet implemented")
    }

    override fun getBoardDetails(conn: Connection?, boardId: Int): Board =
        source.boards[boardId] ?: throw Error("No board")

}