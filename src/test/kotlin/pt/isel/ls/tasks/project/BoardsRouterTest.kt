package pt.isel.ls.tasks.project

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.api.routers.boards.models.BoardDTO
import pt.isel.ls.tasks.api.routers.boards.models.BoardIdDTO
import pt.isel.ls.tasks.api.routers.boards.models.BoardListsDTO
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BoardsRouterTest : InstanceProjectTest() {

    @Test
    fun `Creates a new valid Board`() {
        val idNToken = services.users.createNewUser("testUser", "tests@gmail.com")
        val requestBody = """
            {
                "name": "Test Board",
                "description": "board description"
            }
        """
        val request = Request(Method.POST, "${path}boards")
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer ${idNToken.token}")
            .body(requestBody)

        send(request)
            .apply {
                assertEquals(Status.CREATED, this.status, "Status was not created")
                val board = Json.decodeFromString<BoardIdDTO>(this.bodyString())
                db.run { conn ->
                    assertTrue(db.boards.hasBoard(conn, board.id), "board does not exist")
                    assertTrue(db.users.hasUserInBoard(conn, idNToken.id), "user was not added to board on creation")
                }
            }
    }

    @Test
    fun `Add a User to a Board`() {
        val idNToken = services.users.createNewUser("testUser", "tests@gmail.com")
        val idNToken2 = services.users.createNewUser("testUser1", "tests1@gmail.com")
        val boardId = services.boards.createNewBoard("TestBoard", "This is a big test Board", idNToken.id)
        val request = Request(Method.POST, "${path}boards/$boardId/users/${idNToken2.id}")
            .header("Authorization", "Bearer ${idNToken.token}")

        send(request)
            .apply {
                assertEquals(Status.OK, this.status, "Status was not ok")
                val board = Json.decodeFromString<String>(this.bodyString())
                assertTrue(board.toBoolean(), "user was not added to board")
                db.run { conn ->
                    assertTrue(db.users.hasUserInBoard(conn, idNToken.id), "user was not added to board")
                }
            }
    }

    @Test
    fun `Get valid board details`() {
        val name = "testUser"
        val email = "test@gmail.com"
        val idNToken = services.users.createNewUser(name, email)
        val nameB = "TestBoard"
        val description = "This is a big test Board"
        val boardId = services.boards.createNewBoard(nameB, description, idNToken.id)

        val request = Request(Method.GET, "${path}boards/$boardId")
            .header("Authorization", "Bearer ${idNToken.token}")

        send(request)
            .apply {
                assertEquals(Status.OK, this.status)
                val board = Json.decodeFromString<BoardDTO>(this.bodyString())
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
        val name = "testUser"
        val email = "test@gmail.com"
        val idNToken = services.users.createNewUser(name, email)
        val nameB = "TestBoard"
        val description = "This is a big test Board"
        val boardId = services.boards.createNewBoard(nameB, description, idNToken.id)
        val nameL1 = "testList"
        val nameL2 = "testList1"
        val list1Id = services.lists.createList(nameL1, boardId, idNToken.id)
        val list2Id = services.lists.createList(nameL2, boardId, idNToken.id)

        val request = Request(Method.GET, "${path}boards/$boardId/lists")
            .header("Authorization", "Bearer ${idNToken.token}")

        send(request)
            .apply {
                assertEquals(Status.OK, this.status)
                val board = Json.decodeFromString<BoardListsDTO>(this.bodyString())
                assertEquals(board.lists[0].id, list1Id)
                assertEquals(board.lists[0].name, nameL1)
                assertEquals(board.lists[0].boardId, boardId)
                assertEquals(board.lists[1].id, list2Id)
                assertEquals(board.lists[1].name, nameL2)
                assertEquals(board.lists[1].boardId, boardId)
            }
    }
}
