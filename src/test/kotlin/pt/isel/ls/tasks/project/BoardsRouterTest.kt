package pt.isel.ls.tasks.project

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.api.routers.boards.models.BoardDTO
import pt.isel.ls.tasks.api.routers.boards.models.BoardListsDTO
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BoardsRouterTest : InstanceProjectTest() {
    @OptIn(ExperimentalSerializationApi::class)
    val format = Json { explicitNulls = false }

    @Test
    fun `Creates a new valid Board`() {
        val requestBody = """
            {
                "name": "Test Board",
                "description": "board description"
            }
        """
        val request = Request(Method.POST, "${path}boards")
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer 9f1e3d11-8c18-4cd7-93fc-985c4794cfd9")
            .body(requestBody)

        send(request)
            .apply {
                assertEquals(Status.CREATED, this.status, "Status was not created")
                val board = format.decodeFromString<BoardDTO>(this.bodyString())
                db.run { conn ->
                    assertTrue(db.boards.hasBoard(conn, board.id), "board does not exist")
                    assertTrue(db.users.hasUserBoards(conn, 1), "user was not added to board on creation")
                }
            }
    }

    @Test
    fun `Add a User to a Board`() {
        val idNToken = services.users.createNewUser("testUser", "tests@gmail.com", "Adsfs123&")
        val idNToken2 = services.users.createNewUser("testUser1", "tests1@gmail.com", "Adsfs123&")
        val boardId = services.boards.createNewBoard("TestBoard", "This is a big test Board", idNToken.id)
        val requestBody = """
            {
                "id": ${idNToken2.id}
            }
        """
        val request = Request(Method.POST, "${path}boards/$boardId/users")
            .header("Authorization", "Bearer ${idNToken.token}")
            .body(requestBody)

        send(request)
            .apply {
                assertEquals(Status.OK, this.status, "Status was not ok")
                val board = format.decodeFromString<String>(this.bodyString())
                assertTrue(board.toBoolean(), "user was not added to board")
                db.run { conn ->
                    assertTrue(db.users.hasUserBoards(conn, idNToken.id), "user was not added to board")
                }
            }
    }

    @Test
    fun `Get valid board details`() {
        val idNToken = services.users.createNewUser("testUser", "test@gmail.com", "Adsfs123&")
        val nameB = "TestBoard"
        val description = "This is a big test Board"
        val boardId = services.boards.createNewBoard(nameB, description, idNToken.id)

        val request = Request(Method.GET, "${path}boards/$boardId")
            .header("Authorization", "Bearer ${idNToken.token}")

        send(request)
            .apply {
                assertEquals(Status.OK, this.status)
                val board = format.decodeFromString<BoardDTO>(this.bodyString())
                assertEquals(boardId, board.id)
                assertEquals(nameB, board.name)
                assertEquals(description, board.description)
                db.run { conn ->
                    assertTrue(db.tokens.hasToken(conn, idNToken.token))
                }
            }
    }

    @Test
    fun `Get the Lists of a Board`() {
        val idNToken = services.users.createNewUser("testUser", "tests@gmail.com", "Adsfs123&")
        val boardId = services.boards.createNewBoard("TestBoard", "This is a big test Board", idNToken.id)
        val nameL1 = "testList"
        val nameL2 = "testList1"
        val list1Id = services.lists.createList(nameL1, boardId, idNToken.id)
        val list2Id = services.lists.createList(nameL2, boardId, idNToken.id)

        val request = Request(Method.GET, "${path}boards/$boardId/lists?fields=cards")
            .header("Authorization", "Bearer ${idNToken.token}")

        send(request)
            .apply {
                assertEquals(Status.OK, this.status)
                val board = format.decodeFromString<BoardListsDTO>(this.bodyString())
                assertEquals(board.lists[0].id, list1Id)
                assertEquals(board.lists[0].name, nameL1)
                assertEquals(board.lists[0].boardId, boardId)
                assertEquals(board.lists[1].id, list2Id)
                assertEquals(board.lists[1].name, nameL2)
                assertEquals(board.lists[1].boardId, boardId)
            }
    }
}
