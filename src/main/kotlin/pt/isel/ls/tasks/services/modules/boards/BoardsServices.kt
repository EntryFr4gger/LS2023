package pt.isel.ls.tasks.services.modules.boards

import pt.isel.ls.tasks.db.TaskData
import pt.isel.ls.tasks.domain.Board
import pt.isel.ls.tasks.domain.Card
import pt.isel.ls.tasks.domain.User
import pt.isel.ls.tasks.services.modules.boards.response.BoardDetailsResponse
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
    fun getBoardDetails(boardId: Int, requestId: Int, fields: List<String>?): BoardDetailsResponse {
        isValidBoardId(boardId)
        isValidUserId(requestId)
        if (fields != null) isValidFieldsBoardDetails(fields)

        return source.run { conn ->
            authorizationBoard(conn, boardId, requestId)
            val board = source.boards.getBoardDetails(conn, boardId)
            var lists: List<_List>? = null
            if (fields?.contains("lists") == true) {
                lists = source.boards.getAllLists(conn, boardId, 0, Int.MAX_VALUE)
            }
            BoardDetailsResponse(board, lists)
        }
    }

    /**
     * Get the lists in a board.
     *
     * @param boardId board unique identifier.
     * @param skip skip database tables.
     * @param limit limit database tables.
     * @param requestId request user unique identifier.
     *
     * @return list of lists in a board.
     * */
    fun getAllLists(boardId: Int, skip: Int, limit: Int, requestId: Int): List<_List> {
        isValidBoardId(boardId)
        isValidUserId(requestId)
        isValidSkipAndLimit(skip, limit)

        return source.run { conn ->
            authorizationBoard(conn, boardId, requestId)

            source.boards.getAllLists(conn, boardId, skip, limit)
        }
    }

    fun getAllCards(boardId: Int, skip: Int, limit: Int, requestId: Int, onlyReturnArchived: Boolean): List<Card> {
        isValidBoardId(boardId)
        isValidUserId(requestId)
        isValidSkipAndLimit(skip, limit)

        return source.run { conn ->
            authorizationBoard(conn, boardId, requestId)

            source.boards.getAllCards(conn, boardId, skip, limit, onlyReturnArchived)
        }
    }

    /**
     * Get the list with the users of a board.
     *
     * @param boardId board unique identifier.
     * @param skip skip database tables.
     * @param limit limit database tables.
     * @param requestId request user unique identifier.
     *
     * @return list of Users in a board.
     * */
    fun getBoardUsers(boardId: Int, skip: Int, limit: Int, requestId: Int): List<User> {
        isValidBoardId(boardId)
        isValidUserId(requestId)
        isValidSkipAndLimit(skip, limit)

        return source.run { conn ->
            authorizationBoard(conn, boardId, requestId)

            source.boards.getBoardUsers(conn, boardId, skip, limit)
        }
    }

    /**
     * Search for the name of the board in the database.
     *
     * @param skip skip tables.
     * @param limit limits the return values.
     * @param name name for the board.
     * @param requestId request user unique identifier.
     *
     * @return list of Boards.
     * */
    fun searchBoards(skip: Int, limit: Int, name: String?, requestId: Int): List<Board> {
        isValidUserId(requestId)
        isValidSkipAndLimit(skip, limit)

        return source.run { conn ->
            source.boards.searchBoards(conn, skip, limit, name ?: "", requestId)
        }
    }

    /**
     * Delete a board.
     *
     * @param boardId board unique identifier.
     * @param requestId request user unique identifier.
     *
     * @return true if it has deleted or false otherwise.
     * */
    fun deleteBoard(boardId: Int, requestId: Int): Board {
        isValidBoardId(boardId)
        isValidUserId(requestId)

        return source.run { conn ->
            authorizationBoard(conn, boardId, requestId)

            source.users.deleteBoardUsers(conn, boardId)
            source.boards.deleteBoard(conn, boardId)
        }
    }
}
