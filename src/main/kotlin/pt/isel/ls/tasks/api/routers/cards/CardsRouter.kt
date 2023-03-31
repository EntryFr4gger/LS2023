package pt.isel.ls.tasks.api.routers.cards

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
import pt.isel.ls.tasks.api.routers.cards.models.CardDTO
import pt.isel.ls.tasks.api.routers.cards.models.CardId
import pt.isel.ls.tasks.api.routers.cards.models.CardListUpdate
import pt.isel.ls.tasks.api.routers.cards.models.CreateCardDTO
import pt.isel.ls.tasks.api.utils.errorCatcher
import pt.isel.ls.tasks.api.utils.FilterToken
import pt.isel.ls.tasks.api.utils.hasOrThrow
import pt.isel.ls.tasks.api.utils.pathOrThrow
import pt.isel.ls.tasks.services.modules.cards.CardsServices

class CardsRouter(private val services: CardsServices, val context: RequestContexts) : TasksRouter {
    companion object {
        fun routes(services: CardsServices, context: RequestContexts) = CardsRouter(services, context).routes
    }
    override val routes = routes(
        ("cards" bind Method.POST to ::createCard).withFilter(FilterToken(context)),
        ("cards/{card_id}" bind Method.PUT to ::updateCard).withFilter(FilterToken(context)),
        ("cards/{card_id}" bind Method.GET to ::getCardInfo).withFilter(FilterToken(context))
    )

    /**
     * Creates a new card
     * requires authentication
     * @param request HTTP request that contains a JSON body with a name and a description and dueDate(optional)
     * and the end board and list id
     * @return HTTP response contains a JSON body with the id
     */
    private fun createCard(request: Request): Response = errorCatcher {
        val card = Json.decodeFromString<CreateCardDTO>(request.bodyString())
        val requestId = context[request].hasOrThrow("user_id")
        val id = services.createNewCard(card.name, card.description, card.dueDate, card.boardId, card.listId, requestId)
        return Response(Status.CREATED)
            .header("content-type", "application/json")
            .body(Json.encodeToString(CardId(id))) // change
    }

    /**
     * Moves a card given a new list
     * requires authentication
     * @param request HTTP request that contains a JSON body with an end list id
     * @return HTTP response contains a JSON body with the new list id
     */
    private fun updateCard(request: Request): Response {
        val cardId = request.pathOrThrow("card_id").toInt()
        val card = Json.decodeFromString<CardListUpdate>(request.bodyString())
        val requestId = context[request].hasOrThrow("user_id")
        val response = services.moveCard(card.lid, cardId, requestId)
        return Response(Status.OK)
    }

    /**
     * Get the detailed information of a card
     * require authorization
     * @param request HTTP request that contains the card id
     * @return HTTP response contains a JSON body with card details
     */
    private fun getCardInfo(request: Request): Response = errorCatcher {
        val cardId = request.pathOrThrow("card_id").toInt()
        val requestId = context[request].hasOrThrow("user_id")
        val card = services.getCardDetails(cardId, requestId)
        return Response(Status.OK)
            .header("content-type", "application/json")
            .body(Json.encodeToString(CardDTO(card)))
    }
}
