package pt.isel.ls.tasks.services.modules.boards

import pt.isel.ls.tasks.db.TaskData
import pt.isel.ls.tasks.domain.Board
import pt.isel.ls.tasks.services.utils.ServicesUtilsDB
import pt.isel.ls.tasks.services.utils.isValidBoardDescription
import pt.isel.ls.tasks.services.utils.isValidBoardId
import pt.isel.ls.tasks.services.utils.isValidBoardName
import pt.isel.ls.tasks.services.utils.isValidUserId
import pt.isel.ls.tasks.domain.List as _List

/**
 * Board Services.
 * */
class BoardsServices(val source: TaskData) {
    private val utils = ServicesUtilsDB(source)

    /**
     * Creates a new board.
     *
     * @param name unique name for the board.
     * @param description board description.
     * @param requestId request user unique identifier.
     *
     * @return board unique identifier.
     * */
    fun createNewBoard(name: String, description: String, requestId: Int): Int {
        isValidBoardName(name)
        isValidBoardDescription(description)

        return source.run { conn ->
            // Authenticate done?

            utils.isBoardNewName(conn, name)

            source.boards.createNewBoard(conn, name, description)
        }
    }

    /**
     * Add a user to the board.
     *
     * @param userId user unique identifier.
     * @param boardId board unique identifier.
     * @param requestId request user unique identifier.
     *
     * @return
     * */
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

    /**
     * Get the detailed information of a board.
     *
     * @param boardId board unique identifier.
     * @param requestId request user unique identifier.
     *
     * @return a Board.
     * */
    fun getBoardDetails(boardId: Int, requestId: Int): Board {
        isValidBoardId(boardId)

        return source.run { conn ->
            source.boards.getBoardDetails(conn, boardId)
        }
    }

    /**
     * Get the lists of a board.
     *
     * @param boardId board unique identifier.
     * @param requestId request user unique identifier.
     *
     * @return list of lists of a board.
     * */
    fun getAllLists(boardId: Int, requestId: Int): List<_List> {
        isValidBoardId(boardId)

        return source.run { conn ->
            source.boards.getAllLists(conn, boardId)
        }
    }
}
