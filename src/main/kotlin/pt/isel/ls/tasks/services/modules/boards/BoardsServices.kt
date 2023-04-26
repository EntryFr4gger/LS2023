package pt.isel.ls.tasks.services.modules.boards

import pt.isel.ls.tasks.db.TaskData
import pt.isel.ls.tasks.domain.Board
import pt.isel.ls.tasks.domain.User
import pt.isel.ls.tasks.services.utils.ServicesUtils
import kotlin.collections.List
import pt.isel.ls.tasks.domain.List as _List

/**
 * Board Services.
 * */
class BoardsServices(source: TaskData) : ServicesUtils(source) {

    /**
     * Creates a new board and adds the user to the created board.
     *
     * @param name unique name for the board.
     * @param description board description.
     * @param requestId request user unique identifier.
     *
     * @return new board unique identifier.
     * */
    fun createNewBoard(name: String, description: String, requestId: Int): Int {
        isValidBoardName(name)
        isValidBoardDescription(description)
        isValidUserId(requestId)

        return source.run { conn ->
            authentication(conn, requestId)

            isBoardNewName(conn, name)

            source.boards.createNewBoard(conn, name, description)
                .also { boardId ->
                    source.boards.addUserToBoard(conn, requestId, boardId)
                }
        }
    }

    /**
     * Add a user to the board.
     *
     * @param userId user unique identifier.
     * @param boardId board unique identifier.
     * @param requestId request user unique identifier.
     *
     * @return true if the user was added to the board, false otherwise.
     * */
    fun addUserToBoard(userId: Int, boardId: Int, requestId: Int): Boolean {
        isValidUserId(userId)
        isValidBoardId(boardId)
        isValidUserId(requestId)

        return source.run { conn ->
            authorizationBoard(conn, boardId, requestId)

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
        isValidUserId(requestId)

        return source.run { conn ->
            authorizationBoard(conn, boardId, requestId)

            source.boards.getBoardDetails(conn, boardId)
        }
    }

    /**
     * Get the lists in a board.
     *
     * @param boardId board unique identifier.
     * @param requestId request user unique identifier.
     *
     * @return list of lists in a board.
     * */
    fun getAllLists(boardId: Int, skip: Int, limit: Int, requestId: Int): List<_List> {
        isValidBoardId(boardId)
        isValidUserId(requestId)

        return source.run { conn ->
            authorizationBoard(conn, boardId, requestId)

            source.boards.getAllLists(conn, boardId, skip, limit)
        }
    }

    /**
     * Get the list with the users of a board.
     *
     * @param boardId board unique identifier.
     * @param requestId request user unique identifier.
     *
     * @return list of Users in a board.
     * */
    fun getBoardUsers(boardId: Int, skip: Int, limit: Int, requestId: Int): List<User> {
        isValidBoardId(boardId)
        isValidUserId(requestId)

        return source.run { conn ->
            authorizationBoard(conn, boardId, requestId)

            source.boards.getBoardUsers(conn, boardId, skip, limit)
        }
    }
}
