package pt.isel.ls.tasks.api.routers.cards

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
import pt.isel.ls.tasks.api.routers.boards.models.CreateBoardDTO
import pt.isel.ls.tasks.services.cards.CardsServices

class CardsRouter(private val services: CardsServices) : IRouter {
    companion object {
        fun routes(services: CardsServices) = CardsRouter(services).routes
    }
    override val routes = routes(
        "boards/{board_id}/lists/{list_id}/cards" bind Method.POST to ::createCard,
        "boards/{board_id}/lists/{list_id}/cards/{card_id}" bind Method.PUT to ::updateCard,
        "boards/{board_id}/lists/{list_id}/cards" bind Method.GET to ::getCards,
        "boards/{board_id}/lists/{list_id}/cards/{card_id}" bind Method.GET to ::getCardInfo,
    )

    // Falta deIsolar
    private fun getCardInfo(request: Request): Response {
        val board_id = request.path("board_id")?.toInt() ?: return Response(Status.BAD_REQUEST)
        val list_id = request.path("list_id")?.toInt() ?: return Response(Status.BAD_REQUEST)
        val card_id = request.path("card_id")?.toInt() ?: return Response(Status.BAD_REQUEST)
        return Response(Status.OK)
            .header("content-type", "application/json")
            .body(Json.encodeToString(emptyList<String>()))
    }

    // Falta deIsolar
    private fun getCards(request: Request): Response {
        val board_id = request.path("board_id")?.toInt() ?: return Response(Status.BAD_REQUEST)
        val list_id = request.path("list_id")?.toInt() ?: return Response(Status.BAD_REQUEST)
        return Response(Status.OK)
            .header("content-type", "application/json")
            .body(Json.encodeToString(emptyList<String>()))
    }

    // Falta deIsolar
    private fun createCard(request: Request): Response {
        val board_id = request.path("board_id")?.toInt() ?: return Response(Status.BAD_REQUEST)
        val std = Json.decodeFromString<CreateBoardDTO>(request.bodyString())
        // MockData.lists.add(ListDTO(MockData.lists.last().id+10,std.name, emptyList())) //rem
        return Response(Status.CREATED)
            .header("content-type", "application/json")
            .body(Json.encodeToString(emptyList<String>())) // change
    }

    private fun updateCard(request: Request): Response {
        val board_id = request.path("board_id")?.toInt() ?: return Response(Status.BAD_REQUEST)
        val std = Json.decodeFromString<CreateBoardDTO>(request.bodyString())
        // MockData.lists.add(ListDTO(MockData.lists.last().id+10,std.name, emptyList())) //rem
        return Response(Status.CREATED)
            .header("content-type", "application/json")
            .body(Json.encodeToString(emptyList<String>())) // change
    }
}
