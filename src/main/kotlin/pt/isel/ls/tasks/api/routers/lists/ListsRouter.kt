package pt.isel.ls.tasks.api.routers.lists

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.RequestContexts
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.bind
import org.http4k.routing.routes
import pt.isel.ls.tasks.api.TasksRouter
import pt.isel.ls.tasks.api.routers.lists.models.CreateListDTO
import pt.isel.ls.tasks.api.routers.lists.models.ListCardsDTO
import pt.isel.ls.tasks.api.routers.lists.models.ListDTO
import pt.isel.ls.tasks.api.routers.lists.models.ListIdDTO
import pt.isel.ls.tasks.api.utils.errorCatcher
import pt.isel.ls.tasks.api.utils.FilterToken
import pt.isel.ls.tasks.api.utils.hasOrThrow
import pt.isel.ls.tasks.api.utils.pathOrThrow
import pt.isel.ls.tasks.services.modules.lists.ListsServices

class ListsRouter(private val services: ListsServices, val context: RequestContexts) : TasksRouter {
    companion object {
        fun routes(services: ListsServices, context: RequestContexts) = ListsRouter(services, context).routes
    }
    override val routes = routes(
        ("lists" bind Method.POST to ::postList).withFilter(FilterToken(context)),
        ("lists/{list_id}" bind Method.GET to ::getListInfo).withFilter(FilterToken(context)),
        ("lists/{list_id}/cards" bind Method.GET to ::getListCards).withFilter(FilterToken(context))
    )

    /**
     * Creates a new list
     * requires authorization
     * @param request HTTP request that contains a JSON body with a name and a board id
     * @return HTTP response contains a JSON body with the id
     */
    private fun postList(request: Request): Response = errorCatcher {
        val listInfo = Json.decodeFromString<CreateListDTO>(request.bodyString())
        val requestId = context[request].hasOrThrow("user_id")
        val listId = services.createList(listInfo.name, listInfo.boardId, requestId)
        return Response(Status.CREATED)
            .header("content-type", "application/json")
            .body(Json.encodeToString(ListIdDTO(listId)))
    }

    /**
     * Get detailed information of a list
     * requires authorization
     * @param request HTTP request that has the list id in the path
     * @return HTTP response contains a JSON body with the list information
     */
    private fun getListInfo(request: Request): Response = errorCatcher {
        val listId = request.pathOrThrow("list_id").toInt()
        val requestId = context[request].hasOrThrow("user_id")
        val lists = services.getListDetails(listId, requestId)
        return Response(Status.OK)
            .header("content-type", "application/json")
            .body(Json.encodeToString(ListDTO(lists)))
    }

    /**
     * Get the set of cards in a list
     * requires authorization
     * @param request HTTP request that has the list id in the path
     * @return HTTP response contains a JSON body with the cards information
     */
    private fun getListCards(request: Request): Response = errorCatcher {
        val listId = request.pathOrThrow("list_id").toInt()
        val requestId = context[request].hasOrThrow("user_id")
        val cards = services.getCardsOfList(listId, requestId)
        return Response(Status.OK)
            .header("content-type", "application/json")
            .body(Json.encodeToString(ListCardsDTO(cards)))
    }
}
