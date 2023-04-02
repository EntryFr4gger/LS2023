package pt.isel.ls.tasks.services.modules.boards

import pt.isel.ls.tasks.db.TaskData
import pt.isel.ls.tasks.domain.Board
import pt.isel.ls.tasks.services.utils.ServicesUtils
import kotlin.collections.List
import pt.isel.ls.tasks.domain.List as _List

/**
 * Board Services.
 * */
class BoardsServices(source: TaskData) : ServicesUtils(source) {

    /**
     * Creates a new board and adds the user that request
     * that new board to it
     *
     * @param name unique name for the board.
     * @param description board description.
     * @param requestId request user unique identifier.
     *
     * @return board unique identifier.
     *
     * @throws ServicesError.InvalidArgumentException name is the worng length.
     * @throws ServicesError.InvalidArgumentException description is the worng length.
     * @throws ServicesError.AuthenticationException if user inst authenticated.
     * @throws ServicesError.AlreadyExistsException if name is in use.
     * */
    fun createNewBoard(name: String, description: String, requestId: Int): Int {
        isValidBoardName(name)
        isValidBoardDescription(description)

        return source.run { conn ->
            authentication(conn, requestId)

            isBoardNewName(conn, name)

            val boardId = source.boards.createNewBoard(conn, name, description)
            source.boards.addUserToBoard(conn, requestId, boardId)
            boardId
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
     *
     * @throws ServicesError.InvalidArgumentException if id isn't correct.
     * @throws ServicesError.InvalidArgumentException if id isn't correct.
     * @throws ServicesError.AuthorizationException if user inst authorized.
     * @throws ServicesError.InvalidArgumentException if id doesn't exist.
     * @throws ServicesError.InvalidArgumentException if id doesn't exist.
     * */
    fun addUserToBoard(userId: Int, boardId: Int, requestId: Int): Boolean {
        isValidUserId(userId)
        isValidBoardId(boardId)

        return source.run { conn ->
            authorizationBoard(conn, boardId, requestId)

            hasUser(conn, userId)
            hasBoard(conn, boardId)

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
     *
     * @throws ServicesError.InvalidArgumentException - if id isn't correct.
     * @throws ServicesError.AuthorizationException - if user inst authorized.
     * */
    fun getBoardDetails(boardId: Int, requestId: Int): Board {
        isValidBoardId(boardId)

        return source.run { conn ->
            authorizationBoard(conn, boardId, requestId)

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
     *
     * @throws ServicesError.InvalidArgumentException - if id isn't correct.
     * @throws ServicesError.AuthorizationException - if user inst authorized.
     * */
    fun getAllLists(boardId: Int, requestId: Int): List<_List> {
        isValidBoardId(boardId)

        return source.run { conn ->
            authorizationBoard(conn, boardId, requestId)

            source.boards.getAllLists(conn, boardId)
        }
    }
}
