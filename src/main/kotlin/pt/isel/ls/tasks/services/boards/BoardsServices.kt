package pt.isel.ls.tasks.services.boards

import pt.isel.ls.tasks.db.TaskData
import pt.isel.ls.tasks.domain.Board

class BoardsServices(source: TaskData) {

    fun createNewBoard(name: String, description: String): Int {
        TODO()
    }

    fun addUserToBoard(userId: Int, boardId: Int): Int {
        TODO()
    }

    fun getUserBoards(userId: Int): List<Board> {
        TODO()
    }

    fun getBoardDetails(boardId: Int): Board {
        TODO()
    }
}
