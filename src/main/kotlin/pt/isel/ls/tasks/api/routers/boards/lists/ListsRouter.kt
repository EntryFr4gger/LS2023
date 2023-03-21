package pt.isel.ls.tasks.api.routers.boards.lists.models

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
import pt.isel.ls.tasks.api.routers.MockData.Companion.lists
import pt.isel.ls.tasks.api.routers.boards.models.Board
import pt.isel.ls.tasks.api.routers.boards.models.BoardID
import pt.isel.ls.tasks.api.routers.boards.models.CreateBoard

class ListsRouter : IRouter {
    companion object{
        fun ListsRoutes() = ListsRouter().routes
    }
    override val routes = routes(
        "boards/{board_id}/lists" bind Method.POST to ::postList,
        "boards/{board_id}/lists" bind Method.GET to ::getLists,
        "boards/{board_id}/lists/{list_id}" bind Method.GET to ::getListInfo
    )

    //Falta deIsolar
    private fun getListInfo(request: Request): Response {
        logRequest(request)
        val board_id = request.path("board_id")?.toInt() ?: return Response(Status.BAD_REQUEST)
        val list_id = request.path("list_id")?.toInt() ?: return Response(Status.BAD_REQUEST)
        return Response(Status.OK)
            .header("content-type", "application/json")
            .body(Json.encodeToString(lists.find { list -> list.id==list_id }))
    }

    //Falta deIsolar
    private fun getLists(request: Request): Response {
        logRequest(request)
        val board_id = request.path("board_id")?.toInt() ?: return Response(Status.BAD_REQUEST)
        return Response(Status.OK)
            .header("content-type", "application/json")
            .body(Json.encodeToString(lists))
    }

    //Falta deIsolar
    private fun postList(request: Request): Response {
        logRequest(request)
        val board_id = request.path("board_id")?.toInt() ?: return Response(Status.BAD_REQUEST)
        val std = Json.decodeFromString<CreateBoard>(request.bodyString())
        lists.add(ListInfo(lists.last().id+10,std.name, emptyList())) //rem
        return Response(Status.CREATED)
            .header("content-type", "application/json")
            .body(Json.encodeToString(lists.last().id)) //change
    }
}