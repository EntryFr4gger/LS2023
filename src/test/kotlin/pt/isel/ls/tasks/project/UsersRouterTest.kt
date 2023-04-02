package pt.isel.ls.tasks.project

import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import kotlinx.serialization.Required
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.apache.http.HttpStatus
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.Matchers
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.api.routers.users.models.UserBoardsDTO
import pt.isel.ls.tasks.api.routers.users.models.UserCreationReturnDTO
import pt.isel.ls.tasks.api.routers.users.models.UserInfoDTO
import kotlin.test.Ignore
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class UsersRouterTest :InstanceProjectTest() {

    @Test
    fun `Create new user valid user`() {
        val requestBody = """
            {
                "name": "Manuel Maria",
                "email": "tes23t@gmail.com"
            }
        """
        val request = Request(Method.POST, "${path}users").
        header("Content-Type", "application/json")
            .body(requestBody)

        send(request)
            .apply {
                assertEquals(Status.CREATED, this.status)
                val user = Json.decodeFromString<UserCreationReturnDTO>(this.bodyString())
                db.run { conn ->
                    assertTrue(db.users.hasUser(conn, user.id))
                }
            }
    }

    @Test
    fun `Get valid user details`() {
        val name = "testUser"
        val email = "test1@gmail.com"
        val idNToken = services.users.createNewUser(name,email)

        val request = Request(Method.GET, "${path}users/${idNToken.first}").
        header("Content-Type", "application/json")

        send(request)
            .apply {
                assertEquals(Status.OK, this.status)
                val user = Json.decodeFromString<UserInfoDTO>(this.bodyString())
                assertEquals(idNToken.first,user.id)
                assertEquals(name,user.name)
                assertEquals(email,user.email)
                db.run { conn ->
                    assertTrue(db.tokens.hasToken(conn,idNToken.second))
                }
            }
    }



    @Test
    fun `Get the all user boards available`() {
        val name = "testUser"
        val email = "test@gmail.com"
        val idNToken = services.users.createNewUser(name,email)

        val boards = listOf(
            services.boards.createNewBoard("testBoard1","this is a test board",idNToken.first),
            services.boards.createNewBoard("testBoard2","this is a test board",idNToken.first),
            services.boards.createNewBoard("testBoard3","this is a test board",idNToken.first)
        )

        val request = Request(Method.GET, "${path}users/${idNToken.first}/boards").
        header("Content-Type", "application/json")
            .header("Authorization", "Bearer ${idNToken.second}")

        send(request)
            .apply {
                assertEquals(Status.OK, this.status)
                val boardsDTO = Json.decodeFromString<UserBoardsDTO>(this.bodyString())
                val boardIds = boardsDTO.boards.map { it.id }
                assertTrue(boards.containsAll(boardIds))
            }
    }
}
