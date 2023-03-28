package pt.isel.ls.tasks.api.routers.lists

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
import pt.isel.ls.tasks.api.routers.lists.models.BoardListsDTO
import pt.isel.ls.tasks.api.routers.lists.models.CreateListDTO
import pt.isel.ls.tasks.api.routers.lists.models.ListDTO
import pt.isel.ls.tasks.api.routers.lists.models.ListIdDTO
import pt.isel.ls.tasks.services.lists.ListsServices

class ListsRouter(private val services: ListsServices) : IRouter {
    companion object {
        fun routes(services: ListsServices) = ListsRouter(services).routes
    }
    override val routes = routes(
        "boards/{board_id}/lists" bind Method.POST to ::postList,
        "boards/{board_id}/lists" bind Method.GET to ::getLists,
        "boards/{board_id}/lists/{list_id}" bind Method.GET to ::getListInfo
    )

    private fun getListInfo(request: Request): Response {
        logRequest(request)
        val board_id = request.path("board_id")?.toInt() ?: return Response(Status.BAD_REQUEST) // useless?
        val list_id = request.path("list_id")?.toInt() ?: return Response(Status.BAD_REQUEST)
        val list = services.getListDetails(list_id)
        return Response(Status.OK)
            .header("content-type", "application/json")
            .body(Json.encodeToString(ListDTO(list)))
    }

    private fun getLists(request: Request): Response {
        logRequest(request)
        val board_id = request.path("board_id")?.toInt() ?: return Response(Status.BAD_REQUEST)
        val lists = services.getAllLists(board_id)
        return Response(Status.OK)
            .header("content-type", "application/json")
            .body(Json.encodeToString(BoardListsDTO(lists)))
    }

    private fun postList(request: Request): Response {
        logRequest(request)
        val board_id = request.path("board_id")?.toInt() ?: return Response(Status.BAD_REQUEST)
        val listInfo = Json.decodeFromString<CreateListDTO>(request.bodyString())
        val listId = services.createList(listInfo.name, board_id)
        return Response(Status.CREATED)
            .header("content-type", "application/json")
            .body(Json.encodeToString(ListIdDTO(listId)))
    }
}
