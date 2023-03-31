package pt.isel.ls.tasks.api.routers.boards

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.http4k.core.*
import org.http4k.routing.bind
import org.http4k.routing.routes
import pt.isel.ls.tasks.api.TasksRouter
import pt.isel.ls.tasks.api.routers.boards.models.*
import pt.isel.ls.tasks.api.utils.errorCatcher
import pt.isel.ls.tasks.api.utils.filterToken
import pt.isel.ls.tasks.api.utils.hasOrThrow
import pt.isel.ls.tasks.api.utils.pathOrThrow
import pt.isel.ls.tasks.services.modules.boards.BoardsServices

class BoardsRouter(private val services: BoardsServices, val context: RequestContexts) : TasksRouter {
    companion object {
        fun routes(services: BoardsServices, context: RequestContexts) = BoardsRouter(services, context).routes
    }
    override val routes = routes(
        ("boards" bind Method.POST to ::postBoard).withFilter(filterToken(context)),
        ("boards/{board_id}/users/{user_id}" bind Method.POST to ::postUserToBoard).withFilter(filterToken(context)),
        ("boards/{board_id}" bind Method.GET to ::getBoard).withFilter(filterToken(context)),
        ("boards/{board_id}/lists" bind Method.GET to ::getLists).withFilter(filterToken(context))
    )

    /**
     * Creates a new board
     * requires authentication
     * @param request HTTP request that contains a JSON body with a name and a description
     * @return HTTP response contains a JSON body with the id
     */
    private fun postBoard(request: Request): Response = errorCatcher {
        val boardInfo = Json.decodeFromString<CreateBoardDTO>(request.bodyString())
        val requestId = context[request].hasOrThrow("user_id")
        val boardID = services.createNewBoard(boardInfo.name, boardInfo.description, requestId)
        return Response(Status.CREATED)
            .header("content-type", "application/json")
            .body(Json.encodeToString(BoardIdDTO(boardID)))
    }

    /**
     * Adds a user to a board
     * requires authorization
     * @param request HTTP request that contains the board id and user id in its path
     * @return HTTP response
     */
    private fun postUserToBoard(request: Request): Response = errorCatcher {
        val boardId = request.pathOrThrow("board_id").toInt()
        val userId = request.pathOrThrow("user_id").toInt()
        val requestId = context[request].hasOrThrow("user_id")
        val response = services.addUserToBoard(userId, boardId, requestId)
        return Response(Status.OK)
    }

    /**
     * Get the detailed information of a board
     * require authorization
     * @param request HTTP request that contains the board id
     * @return HTTP response contains a JSON body with board details
     */
    private fun getBoard(request: Request): Response = errorCatcher {
        val boardId = request.pathOrThrow("board_id").toInt()
        val requestId = context[request].hasOrThrow("user_id")
        val board = services.getBoardDetails(boardId, requestId)
        return Response(Status.OK)
            .header("content-type", "application/json")
            .body(Json.encodeToString(BoardDTO(board)))
    }

    /**
     * Get the lists of a board
     * require authorization
     * @param request HTTP request that contains the board id
     * @return HTTP response contains a JSON body with the lists
     */
    private fun getLists(request: Request): Response = errorCatcher {
        val boardId = request.pathOrThrow("board_id").toInt()
        val requestId = context[request].hasOrThrow("user_id")
        val lists = services.getAllLists(boardId, requestId)
        return Response(Status.OK)
            .header("content-type", "application/json")
            .body(Json.encodeToString(BoardListsDTO(lists)))
    }
}
