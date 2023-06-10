package pt.isel.ls.tasks.api.routers.boards

import kotlinx.serialization.json.Json
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.bind
import org.http4k.routing.routes
import pt.isel.ls.tasks.api.routers.TasksRouter
import pt.isel.ls.tasks.api.routers.boards.models.BoardCardsDTO
import pt.isel.ls.tasks.api.routers.boards.models.BoardDTO
import pt.isel.ls.tasks.api.routers.boards.models.BoardListsDTO
import pt.isel.ls.tasks.api.routers.boards.models.BoardUsersDTO
import pt.isel.ls.tasks.api.routers.boards.models.CreateBoardDTO
import pt.isel.ls.tasks.api.routers.users.models.UserBoardsDTO
import pt.isel.ls.tasks.api.routers.users.models.UserIdDTO
import pt.isel.ls.tasks.api.utils.Responde
import pt.isel.ls.tasks.api.utils.TokenUtil
import pt.isel.ls.tasks.api.utils.errorCatcher
import pt.isel.ls.tasks.api.utils.hasOrThrow
import pt.isel.ls.tasks.api.utils.limitOrDefault
import pt.isel.ls.tasks.api.utils.pathOrThrow
import pt.isel.ls.tasks.api.utils.skipOrDefault
import pt.isel.ls.tasks.services.modules.boards.BoardsServices

class BoardsRouter(private val services: BoardsServices, private val tokenHandeler: TokenUtil) : TasksRouter {
    companion object {
        private const val DEFAULT_SKIP = 0
        private const val DEFAULT_LIMIT = Int.MAX_VALUE

        fun routes(services: BoardsServices, tokenHandeler: TokenUtil) = BoardsRouter(services, tokenHandeler).routes
    }

    override val routes = routes(
        "boards" bind Method.POST to ::postBoard,
        "boards/{board_id}/users" bind Method.POST to ::postUserToBoard,
        "boards/{board_id}" bind Method.GET to ::getBoardDetails,
        "boards/{board_id}/lists" bind Method.GET to ::getLists,
        "boards/{board_id}/users" bind Method.GET to ::getBoardUsers,
        "boards/{board_id}/cards" bind Method.GET to ::getBoardCards,
        "boards" bind Method.GET to ::searchBoards,
        "boards/{board_id}" bind Method.DELETE to ::deleteBoard
    ).withFilter(tokenHandeler::filter)

    /**
     * Creates a new board.
     * Require authorization.
     *
     * @param request HTTP request that contains a JSON body with a name and a description
     *
     * @return HTTP response contains a JSON body with the id
     */
    private fun postBoard(request: Request): Response = errorCatcher {
        val boardInfo = Json.decodeFromString<CreateBoardDTO>(request.bodyString())
        val requestId = tokenHandeler.context[request].hasOrThrow("user_id")
        val boardID = services.createNewBoard(boardInfo.name, boardInfo.description, requestId)
        return Responde(Status.CREATED, BoardDTO(boardID))
    }

    /**
     * Adds a user to a board.
     * Require authorization.
     *
     * @param request HTTP request that contains the board id and user id in its path
     *
     * @return HTTP response
     */
    private fun postUserToBoard(request: Request): Response = errorCatcher {
        val userId = Json.decodeFromString<UserIdDTO>(request.bodyString())
        val boardId = request.pathOrThrow("board_id").toInt()
        val requestId = tokenHandeler.context[request].hasOrThrow("user_id")
        val response = services.addUserToBoard(userId.id, boardId, requestId)
        return Responde(Status.OK, response.toString())
    }

    /**
     * Get the detailed information of a board.
     * Require authorization.
     *
     * @param request HTTP request that contains the board id
     *
     * @return HTTP response contains a JSON body with board details
     */
    private fun getBoardDetails(request: Request): Response = errorCatcher {
        val boardId = request.pathOrThrow("board_id").toInt()
        val requestId = tokenHandeler.context[request].hasOrThrow("user_id")
        val fields = request.query("fields")?.split(",")
        val boardResponse = services.getBoardDetails(boardId, requestId, fields)
        return Responde(Status.OK, BoardDTO(boardResponse))
    }

    /**
     * Get the lists of a board.
     * Require authorization.
     *
     * @param request HTTP request that contains the board id
     *
     * @return HTTP response contains a JSON body with the lists
     */
    private fun getLists(request: Request): Response = errorCatcher {
        val boardId = request.pathOrThrow("board_id").toInt()
        val requestId = tokenHandeler.context[request].hasOrThrow("user_id")
        val lists =
            services.getAllLists(
                boardId,
                request.skipOrDefault(DEFAULT_SKIP),
                request.limitOrDefault(DEFAULT_LIMIT),
                requestId
            )
        return Responde(Status.OK, BoardListsDTO(lists))
    }

    /**
     * Get the list with the users of a board.
     * Require authorization.
     *
     * @param request HTTP request that contains the board id
     *
     * @return list of Users in a board.
     * */
    private fun getBoardUsers(request: Request): Response = errorCatcher {
        val boardId = request.pathOrThrow("board_id").toInt()
        val requestId = tokenHandeler.context[request].hasOrThrow("user_id")
        val users =
            services.getBoardUsers(
                boardId,
                request.skipOrDefault(DEFAULT_SKIP),
                request.limitOrDefault(DEFAULT_LIMIT),
                requestId
            )
        return Responde(Status.OK, BoardUsersDTO(users))
    }

    /**
     * Gets the list of a cards for a given board.
     *
     * @param request HTTP request that contains the name to search
     *
     * @return list of Cards.
     * */
    private fun getBoardCards(request: Request): Response = errorCatcher {
        val boardId = request.pathOrThrow("board_id").toInt()
        val requestId = tokenHandeler.context[request].hasOrThrow("user_id")
        val onlyReturnArchived = request.uri.query.contains("archived")
        val cards =
            services.getAllCards(
                boardId,
                request.skipOrDefault(DEFAULT_SKIP),
                request.limitOrDefault(DEFAULT_LIMIT),
                requestId,
                onlyReturnArchived
            )
        return Responde(Status.OK, BoardCardsDTO(cards))
    }

    /**
     * Search for the name of the board in the database.
     *
     * @param request HTTP request that contains the name to search
     *
     * @return list of Boards.
     * */
    private fun searchBoards(request: Request): Response = errorCatcher {
        val name = request.query("name")
        val requestId = tokenHandeler.context[request].hasOrThrow("user_id")
        val boards =
            services.searchBoards(
                request.skipOrDefault(DEFAULT_SKIP),
                request.limitOrDefault(DEFAULT_LIMIT),
                name,
                requestId
            )
        return Responde(Status.OK, UserBoardsDTO(boards))
    }

    /**
     * Delete a board.
     * Require authorization.
     *
     * @param request HTTP request that contains the board id
     *
     * @return HTTP response with OK status
     * */
    private fun deleteBoard(request: Request): Response = errorCatcher {
        val boardId = request.pathOrThrow("board_id").toInt()
        val requestId = tokenHandeler.context[request].hasOrThrow("user_id")
        val board = services.deleteBoard(boardId, requestId)
        return Responde(Status.OK, BoardDTO(board))
    }
}
