package pt.isel.ls.tasks.api.routers.lists

import kotlinx.serialization.json.Json
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.bind
import org.http4k.routing.routes
import pt.isel.ls.tasks.api.routers.TasksRouter
import pt.isel.ls.tasks.api.routers.cards.models.CardId
import pt.isel.ls.tasks.api.routers.lists.models.CardReposition
import pt.isel.ls.tasks.api.routers.lists.models.CreateListDTO
import pt.isel.ls.tasks.api.routers.lists.models.ListCardsDTO
import pt.isel.ls.tasks.api.routers.lists.models.ListDTO
import pt.isel.ls.tasks.api.utils.Responde
import pt.isel.ls.tasks.api.utils.TokenUtil
import pt.isel.ls.tasks.api.utils.errorCatcher
import pt.isel.ls.tasks.api.utils.hasOrThrow
import pt.isel.ls.tasks.api.utils.limitOrDefault
import pt.isel.ls.tasks.api.utils.pathOrThrow
import pt.isel.ls.tasks.api.utils.skipOrDefault
import pt.isel.ls.tasks.services.modules.lists.ListsServices

class ListsRouter(private val services: ListsServices, private val tokenHandeler: TokenUtil) : TasksRouter {
    companion object {
        private const val DEFAULT_SKIP = 0
        private const val DEFAULT_LIMIT = Int.MAX_VALUE

        fun routes(services: ListsServices, tokenHandeler: TokenUtil) = ListsRouter(services, tokenHandeler).routes
    }

    override val routes = routes(
        "lists" bind Method.POST to ::postList,
        "lists/{list_id}" bind Method.GET to ::getListDetails,
        "lists/{list_id}/cards" bind Method.GET to ::getListCards,
        "lists/{list_id}/cards" bind Method.PUT to ::respositionCard,
        "lists/{list_id}" bind Method.DELETE to ::deleteList
    ).withFilter(tokenHandeler::filter)

    /**
     * Creates a new list.
     * Requires authorization.
     *
     * @param request HTTP request that contains a JSON body with a name and a board id
     *
     * @return HTTP response contains a JSON body with the id
     */
    private fun postList(request: Request): Response = errorCatcher {
        val listInfo = Json.decodeFromString<CreateListDTO>(request.bodyString())
        val requestId = tokenHandeler.context[request].hasOrThrow("user_id")
        val listId = services.createList(listInfo.name, listInfo.boardId, requestId)
        return Responde(Status.CREATED, ListDTO(listId))
    }

    /**
     * Moves a card given a new card position.
     * requires authentication.
     *
     * @param request HTTP request that contains a JSON body with an end list id
     *
     * @return HTTP response contains a JSON body with the new list id
     */
    private fun respositionCard(request: Request): Response = errorCatcher {
        val listId = request.pathOrThrow("list_id").toInt()
        val cardReq = Json.decodeFromString<CardReposition>(request.bodyString())
        val requestId = tokenHandeler.context[request].hasOrThrow("user_id")
        val cardId = services.respositionCard(listId, cardReq.cardId, cardReq.cix, requestId)
        return Responde(Status.OK, CardId(cardId))
    }

    /**
     * Get detailed information of a list.
     * Requires authorization.
     *
     * @param request HTTP request that has the list id in the path
     *
     * @return HTTP response contains a JSON body with the list information
     */
    private fun getListDetails(request: Request): Response = errorCatcher {
        val listId = request.pathOrThrow("list_id").toInt()
        val requestId = tokenHandeler.context[request].hasOrThrow("user_id")
        val fields = request.query("fields")?.split(",") ?: emptyList()
        val listResponse = services.getListDetails(listId, requestId, fields)
        return Responde(Status.OK, ListDTO(listResponse))
    }

    /**
     * Get the set of cards in a list.
     * Requires authorization.
     *
     * @param request HTTP request that has the list id in the path
     *
     * @return HTTP response contains a JSON body with the cards information
     */
    private fun getListCards(request: Request): Response = errorCatcher {
        val listId = request.pathOrThrow("list_id").toInt()
        val requestId = tokenHandeler.context[request].hasOrThrow("user_id")
        val cards =
            services.getCardsOfList(
                listId,
                request.skipOrDefault(DEFAULT_SKIP),
                request.limitOrDefault(DEFAULT_LIMIT),
                requestId
            )
        return Responde(Status.OK, ListCardsDTO(cards))
    }

    /**
     * Delete a list.
     * Requires authorization.
     *
     * @param request HTTP request that has the list id in the path
     *
     * @return HTTP response with OK status
     * */
    private fun deleteList(request: Request): Response = errorCatcher {
        val listId = request.pathOrThrow("list_id").toInt()
        val requestId = tokenHandeler.context[request].hasOrThrow("user_id")
        val list = services.deleteList(listId, requestId)
        return Responde(Status.OK, ListDTO(list))
    }
}
