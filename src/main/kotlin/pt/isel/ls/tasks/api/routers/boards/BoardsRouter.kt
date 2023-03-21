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
import pt.isel.ls.tasks.api.logRequest
import pt.isel.ls.tasks.api.routers.IRouter
import pt.isel.ls.tasks.api.routers.MockData
import pt.isel.ls.tasks.api.routers.MockData.Companion.userBoards
import pt.isel.ls.tasks.api.routers.users.models.RequestPostUser
import pt.isel.ls.tasks.api.routers.users.models.ReturnGetUser
import pt.isel.ls.tasks.api.routers.users.models.ReturnPostUser

class BoardsRouter : IRouter {
    companion object{
        fun BoardsRoutes() = BoardsRouter().routes
    }
    override val routes = routes(
        "board" bind Method.POST to ::postBoard,
        "board/{board_id}/users/{user_id}" bind Method.PUT to ::putUser,
        "boards/{board_id}" bind Method.GET to ::getBoard,
        "users/{user_id}/boards" bind Method.GET to ::getUserBoards

    )

    private fun getUserBoards(request: Request): Response {
        logRequest(request)
        val user_id = request.path("user_id")?.toInt() ?: -1
        return Response(Status.OK)
            .header("content-type", "application/json")
            .body(Json.encodeToString(userBoards))
    }

    private fun getBoard(request: Request): Response {
        logRequest(request)
        val board_id = request.path("board_id")?.toInt() ?: -1
        return Response(Status.OK)
            .header("content-type", "application/json")
            .body(Json.encodeToString(MockData.boards.find { board -> board.id==board_id }))
    }

    private fun putUser(request: Request): Response {
        logRequest(request)
        val std = Json.decodeFromString<RequestPostUser>(request.bodyString())
        //MockData.boards.add(ReturnGetUser(MockData.boards.last().id+10,std.name,std.email)) //rem
        return Response(Status.CREATED)
            .header("content-type", "application/json")
            .body(Json.encodeToString(ReturnPostUser(std)))
    }

    private fun postBoard(request: Request): Response {
        logRequest(request)
        val std = Json.decodeFromString<RequestPostUser>(request.bodyString())
        //.boards.add(ReturnGetUser(MockData.boards.last().id+10,std.name,std.email)) //rem
        return Response(Status.CREATED)
            .header("content-type", "application/json")
            .body(Json.encodeToString(ReturnPostUser(std)))
    }
}