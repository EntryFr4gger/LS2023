package pt.isel.ls.tasks.api.routers.boards

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes
import pt.isel.ls.tasks.api.routers.IRouter
import pt.isel.ls.tasks.api.routers.boards.models.BoardDTO
import pt.isel.ls.tasks.api.routers.boards.models.BoardIdDTO
import pt.isel.ls.tasks.api.routers.boards.models.CreateBoardDTO
import pt.isel.ls.tasks.api.routers.boards.models.UserBoardsDTO
import pt.isel.ls.tasks.services.modules.boards.BoardsServices

class BoardsRouter(private val services: BoardsServices) : IRouter {
    companion object {
        fun routes(services: BoardsServices) = BoardsRouter(services).routes
    }
    override val routes = routes(
        "board" bind Method.POST to ::postBoard,
        "board/{board_id}/users/{user_id}" bind Method.POST to ::postUserToBoard,
        "boards/{board_id}" bind Method.GET to ::getBoard,
        "users/{user_id}/boards" bind Method.GET to ::getUserBoards

    )

    private fun getUserBoards(request: Request): Response {
        val  userId = request.path("user_id")?.toIntOrNull() ?: return Response(Status.BAD_REQUEST).body("ID not valid")
        val boards = services.getUserBoards(userId)
        return Response(Status.OK)
            .header("content-type", "application/json")
            .body(Json.encodeToString(UserBoardsDTO(boards)))
    }

    private fun getBoard(request: Request): Response {
        val boardId = request.path("board_id")?.toIntOrNull() ?: return Response(Status.BAD_REQUEST).body("ID not valid")
        val board = services.getBoardDetails(boardId)
        return Response(Status.OK)
            .header("content-type", "application/json")
            .body(Json.encodeToString(BoardDTO(board)))
    }

    // trocar o user ID para o corpo?
    private fun postUserToBoard(request: Request): Response {
        val boardId = request.path("board_id")?.toIntOrNull() ?: return Response(Status.BAD_REQUEST).body("ID not valid")
        val userId = request.path("user_id")?.toIntOrNull() ?: return Response(Status.BAD_REQUEST).body("ID not valid")
        //val response = services.addUserToBoard(userId, boardId) // can be removed but can be useful
        return Response(Status.OK)
    }

    private fun postBoard(request: Request): Response {
        val boardInfo = Json.decodeFromString<CreateBoardDTO>(request.bodyString())
        val boardID = services.createNewBoard(boardInfo.name, boardInfo.description)
        return Response(Status.CREATED)
            .header("content-type", "application/json")
            .body(Json.encodeToString(BoardIdDTO(boardID)))
    }
}
