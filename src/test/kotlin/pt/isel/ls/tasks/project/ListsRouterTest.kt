package pt.isel.ls.tasks.project

import kotlinx.datetime.LocalDate
import kotlinx.serialization.json.Json
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.api.routers.boards.models.BoardDTO
import pt.isel.ls.tasks.api.routers.lists.models.ListCardsDTO
import pt.isel.ls.tasks.api.routers.lists.models.ListDTO
import pt.isel.ls.tasks.api.utils.MessageDTO
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ListsRouterTest : InstanceProjectTest() {

    @Test
    fun `Creates a new list`() {
        val idNToken = services.users.createNewUser("testUser", "tests@gmail.com", "Adsfs123&")
        val boardId = services.boards.createNewBoard("testBoard1", "this is a test board", idNToken.id)
        val requestBody = """
            {
             "name":"Lista de teste",
             "boardId": $boardId
             }
         """
        val request = Request(Method.POST, "${path}lists")
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer ${idNToken.token}")
            .body(requestBody)

        send(request)
            .apply {
                assertEquals(Status.CREATED, this.status)
                val list = format.decodeFromString<BoardDTO>(this.bodyString())
                db.run { conn ->
                    assertTrue(db.lists.hasList(conn, list.id))
                }
            }
    }

    @Test
    fun `Get list details`() {
        val idNToken = services.users.createNewUser("testUser", "tests@gmail.com", "Adsfs123&")
        val boardId = services.boards.createNewBoard("testBoard1", "this is a test board", idNToken.id)
        val nameL1 = "testList"
        val list1Id = services.lists.createList(nameL1, boardId, idNToken.id)

        val request = Request(Method.GET, "${path}lists/$list1Id")
            .header("Authorization", "Bearer ${idNToken.token}")

        send(request)
            .apply {
                assertEquals(Status.OK, this.status)
                val list = format.decodeFromString<ListDTO>(this.bodyString())
                assertEquals(list1Id, list.id)
                assertEquals(nameL1, list.name)
                assertEquals(boardId, list.boardId)
            }
    }

    @Test
    fun `Get the cards in a list`() {
        val idNToken = services.users.createNewUser("testUser", "tests@gmail.com", "Adsfs123&")
        val boardId = services.boards.createNewBoard("testBoard1", "this is a test board", idNToken.id)
        val list1Id = services.lists.createList("testList", boardId, idNToken.id)

        val exampleLD = LocalDate(2023, 3, 21)

        val cards = listOf(
            services.cards.createNewCard("card1", "card 1 description", exampleLD, boardId, list1Id, idNToken.id),
            services.cards.createNewCard("card2", "card 2 description", exampleLD, boardId, list1Id, idNToken.id),
            services.cards.createNewCard("card3", "card 3 description", exampleLD, boardId, list1Id, idNToken.id)
        )

        val request = Request(Method.GET, "${path}lists/$list1Id/cards")
            .header("Authorization", "Bearer ${idNToken.token}")

        send(request)
            .apply {
                assertEquals(Status.OK, this.status)
                val cardsDTO = Json.decodeFromString<ListCardsDTO>(this.bodyString())
                val cardIds = cardsDTO.cards.map { it.id }
                assertTrue { cards.containsAll(cardIds) }
            }
    }

    @Test
    fun `Delete list details`() {
        val idNToken = services.users.createNewUser("testUser", "tests@gmail.com", "Adsfs123&")
        val boardId = services.boards.createNewBoard("testBoard1", "this is a test board", idNToken.id)
        val list1Id = services.lists.createList("testList", boardId, idNToken.id)

        val request = Request(Method.DELETE, "${path}lists/$list1Id")
            .header("Authorization", "Bearer ${idNToken.token}")

        send(request)
            .apply {
                assertEquals(Status.OK, this.status)
                val list = format.decodeFromString<MessageDTO>(this.bodyString())
                assertTrue(list.sucess)
            }
    }

    @Test
    fun ` Moves a card given a new card position`() {
        val idNToken = services.users.createNewUser("testUser", "tests@gmail.com", "Adsfs123&")
        val boardId = services.boards.createNewBoard("testBoard1", "this is a test board", idNToken.id)
        val list1Id = services.lists.createList("testList", boardId, idNToken.id)
        val exampleLD = LocalDate(2023, 3, 21)

        val cards = listOf(
            services.cards.createNewCard("card1", "card 1 description", exampleLD, boardId, list1Id, idNToken.id),
            services.cards.createNewCard("card2", "card 2 description", exampleLD, boardId, list1Id, idNToken.id),
            services.cards.createNewCard("card3", "card 3 description", exampleLD, boardId, list1Id, idNToken.id)
        )

        val requestBody = """
            {
                "lid":$list1Id
            }
         """

        val request = Request(Method.PUT, "${path}cards/${cards[2]}")
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer ${idNToken.token}")
            .body(requestBody)

        send(request)
            .apply {
                assertEquals(Status.OK, this.status)
                val list = format.decodeFromString<MessageDTO>(this.bodyString())
                assertTrue(list.sucess)
            }
    }
}
