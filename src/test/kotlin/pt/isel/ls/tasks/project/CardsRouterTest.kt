package pt.isel.ls.tasks.project

import kotlinx.datetime.LocalDate
import kotlinx.serialization.json.Json
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.api.routers.cards.models.CardDTO
import pt.isel.ls.tasks.api.routers.cards.models.CardId
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CardsRouterTest : InstanceProjectTest() {
    @Test
    fun `Creates a new card`() {
        val idNToken = services.users.createNewUser("testUser", "tests@gmail.com", "Adsfs123&")
        val boardId = services.boards.createNewBoard("testBoard1", "this is a test board", idNToken.id)
        val list1Id = services.lists.createList("testList", boardId, idNToken.id)

        val requestBody = """
            {
             "name": "card de teste",
             "description": "Life is good"
             "boardId": $boardId
             "listId": $list1Id
             }
         """
        val request = Request(Method.POST, "${path}cards")
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer ${idNToken.token}")
            .body(requestBody)

        send(request)
            .apply {
                assertEquals(Status.CREATED, this.status)
                val cardId = Json.decodeFromString<CardId>(this.bodyString())
                db.run { conn ->
                    assertTrue(db.cards.hasCard(conn, cardId.id))
                }
            }
    }

    @Test
    fun `Get the detailed information of a card `() {
        val idNToken = services.users.createNewUser("testUser", "tests@gmail.com", "Adsfs123&")
        val boardId = services.boards.createNewBoard("testBoard1", "this is a test board", idNToken.id)
        val listId = services.lists.createList("testList", boardId, idNToken.id)
        val exampleLD = LocalDate(2023, 3, 21) // optional
        val cardName = "Card 1"
        val cardDescription = "Card 1 description"
        val cardId = services.cards.createNewCard(cardName, cardDescription, exampleLD, boardId, listId, idNToken.id)

        val request = Request(Method.GET, "${path}cards/$cardId")
            .header("Authorization", "Bearer ${idNToken.token}")

        send(request)
            .apply {
                assertEquals(Status.OK, this.status)
                val card = Json.decodeFromString<CardDTO>(this.bodyString())
                assertEquals(cardId, card.id)
                assertEquals(cardName, card.name)
                assertEquals(cardDescription, card.description)
                assertEquals(exampleLD, card.dueDate)
            }
    }

    @Test
    fun `Moves a card given a new list`() {
        val idNToken = services.users.createNewUser("testUser", "tests@gmail.com", "Adsfs123&")
        val boardId = services.boards.createNewBoard("testBoard1", "this is a test board", idNToken.id)
        val list1Id = services.lists.createList("testList 1", boardId, idNToken.id)
        val list2Id = services.lists.createList("testList 2", boardId, idNToken.id)
        val exampleLD = LocalDate(2023, 3, 21)
        val cardName = "Card 1"
        val cardDescription = "Card 1 description"
        val cardId =
            services.cards.createNewCard(cardName, cardDescription, exampleLD, boardId, list1Id, idNToken.id)

        val requestBody = """
            {
             "lid": $list2Id
             }
         """

        val request = Request(Method.PUT, "${path}cards/$cardId")
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer ${idNToken.token}")
            .body(requestBody)

        send(request)
            .apply {
                assertEquals(Status.OK, this.status)
                db.run { conn ->
                    assertEquals(list2Id, db.cards.getCardDetails(conn, cardId).listId)
                }
            }
    }

    @Test
    fun `Delete a card given its id`() {
        val idNToken = services.users.createNewUser("testUser", "tests@gmail.com", "Adsfs123&")
        val boardId = services.boards.createNewBoard("testBoard1", "this is a test board", idNToken.id)
        val listId = services.lists.createList("testList 1", boardId, idNToken.id)
        val exampleLD = LocalDate(2023, 3, 21)
        val cardName = "Card 1"
        val cardDescription = "Card 1 description"
        val cardId =
            services.cards.createNewCard(cardName, cardDescription, exampleLD, boardId, listId, idNToken.id)

        val request = Request(Method.DELETE, "${path}cards/$cardId")
            .header("Authorization", "Bearer ${idNToken.token}")

        send(request)
            .apply {
                assertEquals(Status.OK, this.status)
                db.run { conn ->
                    assertTrue { db.lists.getAllCards(conn, listId, 0, 10).isEmpty() }
                }
            }
    }
}
