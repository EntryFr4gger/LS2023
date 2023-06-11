package pt.isel.ls.tasks.project

import kotlinx.serialization.Serializable
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.api.routers.users.models.UserBoardsDTO
import pt.isel.ls.tasks.api.routers.users.models.UserDTO
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class UsersRouterTest : InstanceProjectTest() {
    @Serializable
    data class UserCreationReturn(val id: Int, val token: String)

    @Test
    fun `Create new user valid user`() {
        val requestBody = """
            {
                "name": "Manuel Maria",
                "email": "tes23t@gmail.com",
                "password": "Adsfs123&"
            }
        """
        val request = Request(Method.POST, "${path}users")
            .header("Content-Type", "application/json")
            .body(requestBody)

        send(request)
            .apply {
                assertEquals(Status.CREATED, this.status)
                val user = format.decodeFromString<UserCreationReturn>(this.bodyString())
                db.run { conn ->
                    assertTrue(db.users.hasUser(conn, user.id))
                }
            }
    }

    @Test
    fun `Get valid user details`() {
        val name = "testUser"
        val email = "test1@gmail.com"
        val password = "Adsfs123&"
        val idNToken = services.users.createNewUser(name, email, password)

        val request = Request(Method.GET, "${path}users/${idNToken.id}")
            .header("Content-Type", "application/json")

        send(request)
            .apply {
                assertEquals(Status.OK, this.status)
                val user = format.decodeFromString<UserDTO>(this.bodyString())
                assertEquals(idNToken.id, user.id)
                assertEquals(name, user.name)
                assertEquals(email, user.email)
                db.run { conn ->
                    assertTrue(db.tokens.hasToken(conn, idNToken.token))
                }
            }
    }

    @Test
    fun `Get the all user boards available`() {
        val idNToken = services.users.createNewUser("testUser", "tests@gmail.com", "Adsfs123&")

        val boards = listOf(
            services.boards.createNewBoard("testBoard1", "this is a test board", idNToken.id),
            services.boards.createNewBoard("testBoard2", "this is a test board", idNToken.id),
            services.boards.createNewBoard("testBoard3", "this is a test board", idNToken.id)
        )

        val request = Request(Method.GET, "${path}users/${idNToken.id}/boards")
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer ${idNToken.token}")

        send(request)
            .apply {
                assertEquals(Status.OK, this.status)
                val boardsDTO = format.decodeFromString<UserBoardsDTO>(this.bodyString())
                val boardIds = boardsDTO.boards.map { it.id }
                assertTrue(boards.containsAll(boardIds))
            }
    }

    @Test
    fun ` Gets all Users in the database`() {
        val idNToken = services.users.createNewUser("testUser", "tests@gmail.com", "Adsfs123&")

        val boards = listOf(
            services.boards.createNewBoard("testBoard1", "this is a test board", idNToken.id),
            services.boards.createNewBoard("testBoard2", "this is a test board", idNToken.id),
            services.boards.createNewBoard("testBoard3", "this is a test board", idNToken.id)
        )

        val request = Request(Method.GET, "${path}users/${idNToken.id}/boards")
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer ${idNToken.token}")

        send(request)
            .apply {
                assertEquals(Status.OK, this.status)
                val boardsDTO = format.decodeFromString<UserBoardsDTO>(this.bodyString())
                val boardIds = boardsDTO.boards.map { it.id }
                assertTrue(boards.containsAll(boardIds))
            }
    }
}
