package pt.isel.ls.tasks.db.modules.boards

import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.domain.Board
import java.lang.Error

class BoardsDataMem(private val source: TasksDataStorage) : BoardsDB {
    init {
        source.boards[1] = Board(1, "ISEL", "Cenas do 4 semestre do isel")
        source.boards[2] = Board(2, "Compras", "Ida ao supermercado")
        source.boards[3] = Board(3, "Limpeza", "O que falta limpar cá em casa")
        source.nextBoardId.addAndGet(3)

        source.userBoard[1] = listOf(0)
        source.userBoard[2] = listOf(1, 2)
    }

    override fun createNewBoard(conn: TransactionManager, name: String, description: String): Int {
        val id = source.nextBoardId.getAndIncrement()

        if (source.boards.values.any { it.name == name }) {
            throw error("Name in use")
        }

        source.boards[id] = Board(id, name, description)
        return id
    }

    // refazer
    // verificar se user existe
    override fun addUserToBoard(conn: TransactionManager, userId: Int, boardId: Int): Int {
        if (source.userBoard.containsKey(userId)) {
            source.userBoard[userId] = source.userBoard[userId]!! + listOf(boardId)
        } else {
            source.userBoard[userId] = listOf(boardId)
        }
        return 0
    }

    override fun getUserBoards(conn: TransactionManager, userId: Int): List<Board> =
        source.userBoard[userId]!!.mapNotNull { source.boards[it] }

    override fun getBoardDetails(conn: TransactionManager, boardId: Int): Board =
        source.boards[boardId] ?: throw Error("No board")
}
