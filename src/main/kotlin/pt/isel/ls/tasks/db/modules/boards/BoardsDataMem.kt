package pt.isel.ls.tasks.db.modules.boards

import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.domain.Board
import java.lang.Error
import java.sql.Connection

class BoardsDataMem(private val source: TasksDataStorage): BoardsDB {
    init {
        source.boards[0] = Board(1, "ISEL", "Cenas do 4 semestre do isel")
        source.boards[1] = Board(2, "Compras", "Ida ao supermercado")
        source.boards[2] = Board(3, "Limpeza", "O que falta limpar cá em casa")


        source.userBoard[0] = listOf(0)
        source.userBoard[1] = listOf(1)
    }

    override fun createNewBoard(conn: Connection, name: String, description: String): Int {
        val id = source.nextBoardId.getAndIncrement()

        if(source.boards.values.any { it.name == name })
            throw error("Name in use")

        source.boards[id] = Board(id, name, description)
        return id
    }
    //refazer
    //verificar se user existe
    override fun addUserToBoard(conn: Connection, userId: Int, boardId: Int): Int {
        if (source.userBoard.containsKey(userId))
            source.userBoard[userId] = source.userBoard[userId]!! + listOf(boardId)
        else
            source.userBoard[userId] = listOf(boardId)
        return 0
    }

    override fun getUserBoards(conn: Connection, userId: Int): List<Board> =
        source.userBoard[userId]!!.mapNotNull { source.boards[it] }

    override fun getBoardDetails(conn: Connection, boardId: Int): Board =
        source.boards[boardId] ?: throw Error("No board")

}