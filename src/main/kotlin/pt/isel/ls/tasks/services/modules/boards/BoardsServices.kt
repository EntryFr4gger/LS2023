package pt.isel.ls.tasks.services.modules.boards

import pt.isel.ls.tasks.db.TaskData
import pt.isel.ls.tasks.domain.Board
import pt.isel.ls.tasks.services.utils.ServicesUtilsDB
import pt.isel.ls.tasks.services.utils.isValidBoardDescription
import pt.isel.ls.tasks.services.utils.isValidBoardId
import pt.isel.ls.tasks.services.utils.isValidBoardName
import pt.isel.ls.tasks.services.utils.isValidUserId

class BoardsServices(val source: TaskData) {
    private val utils = ServicesUtilsDB(source)

    fun createNewBoard(name: String, description: String, requestId: Int): Int {
        isValidBoardName(name)
        isValidBoardDescription(description)

        return source.run { conn ->
            // Authenticate done?

            utils.isBoardNewName(conn, name)

            source.boards.createNewBoard(conn, name, description)
        }
    }

    fun addUserToBoard(userId: Int, boardId: Int, requestId: Int): Int {
        isValidUserId(userId)
        isValidBoardId(boardId)

        return source.run { conn ->
            utils.hasUser(conn, userId)
            utils.hasBoard(conn, boardId)
            // Verify if user is already in board?

            source.boards.addUserToBoard(conn, userId, boardId)
        }
    }

    fun getBoardDetails(boardId: Int, requestId: Int): Board {
        isValidBoardId(boardId)

        return source.run { conn ->
            source.boards.getBoardDetails(conn, boardId)
        }
    }

    fun getAllLists(boardId: Int, requestId: Int): List<pt.isel.ls.tasks.domain.List> {
        isValidBoardId(boardId)

        return source.run { conn ->
            source.boards.getAllLists(conn, boardId)
        }
    }
}
