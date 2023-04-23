package pt.isel.ls.tasks.db.modules.boards

import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.errors.NotFoundException
import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.domain.Board
import pt.isel.ls.tasks.domain.User
import pt.isel.ls.tasks.domain.List as _List

class BoardsDataMem(private val source: TasksDataStorage) : BoardsDB {

    init {
        source.boards[1] = Board(1, "ISEL", "Cenas do 4 semestre do isel")
        source.boards[2] = Board(2, "Compras", "Ida ao supermercado")
        source.boards[3] = Board(3, "Limpeza", "O que falta limpar cá em casa")
        source.nextBoardId.addAndGet(3)

        source.userBoard[1] = listOf(1,2,3)
        source.userBoard[2] = listOf(1, 2)
    }

    override fun createNewBoard(conn: TransactionManager, name: String, description: String): Int {
        val id = source.nextBoardId.getAndIncrement()
        source.boards[id] = Board(id, name, description)
        return id
    }

    override fun addUserToBoard(conn: TransactionManager, userId: Int, boardId: Int): Boolean {
        val userBoard = source.userBoard[userId]
        if (source.userBoard.containsKey(userId)) {
            if (userBoard != null) {
                source.userBoard[userId] = userBoard + listOf(boardId)
            }
        } else {
            source.userBoard[userId] = listOf(boardId)
        }
        return !source.userBoard[userId].isNullOrEmpty()
    }

    override fun getBoardDetails(conn: TransactionManager, boardId: Int): Board =
        source.boards[boardId] ?: throw NotFoundException()

    override fun getAllLists(conn: TransactionManager, boardId: Int): List<_List> =
        source.lists.toList().mapNotNull {
            it.second.takeIf { list ->
                list.boardId == boardId
            }
        }

    override fun getBoardUsers(conn: TransactionManager, boardId: Int): List<User> {
        TODO("Not yet implemented")
    }

    override fun hasBoardName(conn: TransactionManager, name: String): Boolean =
        source.boards.values.find { it.name == name } != null

    override fun hasBoard(conn: TransactionManager, boardId: Int): Boolean =
        source.boards[boardId] != null
}
