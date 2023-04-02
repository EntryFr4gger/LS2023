package pt.isel.ls.tasks.project

import kotlinx.datetime.LocalDate
import kotlinx.serialization.decodeFromString
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
        val name = "testUser"
        val email = "test1@gmail.com"
        val idNToken = services.users.createNewUser(name, email)
        val boardId = services.boards.createNewBoard("testBoard1", "this is a test board", idNToken.first)
        val nameL1 = "testList"
        val list1Id = services.lists.createList(nameL1, boardId, idNToken.first)
        val exampleLD = LocalDate(2023, 3, 21) // optional

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
            .header("Authorization", "Bearer ${idNToken.second}")
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
        val userName = "testUser"
        val email = "test1@gmail.com"
        val idNToken = services.users.createNewUser(userName, email)
        val boardId = services.boards.createNewBoard("testBoard1", "this is a test board", idNToken.first)
        val listName = "testList"
        val listId = services.lists.createList(listName, boardId, idNToken.first)
        val exampleLD = LocalDate(2023, 3, 21) // optional
        val cardName = "Card 1"
        val cardDescription = "Card 1 description"
        val cardId = services.cards.createNewCard(cardName,cardDescription,exampleLD,boardId,listId,idNToken.first)

        val request = Request(Method.GET, "${path}cards/$cardId")
            .header("Authorization", "Bearer ${idNToken.second}")

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
        val userName = "testUser"
        val email = "test1@gmail.com"
        val idNToken = services.users.createNewUser(userName, email)
        val boardId = services.boards.createNewBoard("testBoard1", "this is a test board", idNToken.first)
        val list1Name = "testList 1"
        val list2Name = "testList 2"
        val list1Id = services.lists.createList(list1Name, boardId, idNToken.first)
        val list2Id = services.lists.createList(list2Name, boardId, idNToken.first)
        val exampleLD = LocalDate(2023, 3, 21) // optional
        val cardName = "Card 1"
        val cardDescription = "Card 1 description"
        val cardId = services.cards.createNewCard(cardName,cardDescription,exampleLD,boardId,list1Id,idNToken.first)

        val requestBody = """
            {
             "lid": $list2Id
             }
         """


        val request = Request(Method.PUT, "${path}cards/$cardId")
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer ${idNToken.second}")
            .body(requestBody)

        send(request)
            .apply {
                assertEquals(Status.OK, this.status)
               // val listIdDTO = Json.decodeFromString<CardListUpdate>(this.bodyString())
                //assertEquals(list2Id, listIdDTO.id)


            }
    }
}
