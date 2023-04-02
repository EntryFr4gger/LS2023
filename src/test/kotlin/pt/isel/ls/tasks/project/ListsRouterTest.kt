package pt.isel.ls.tasks.project

import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import kotlinx.serialization.Required
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.apache.http.HttpStatus
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import kotlin.test.Ignore

class ListsRouterTest : InstanceProjectTest() {
    private val listId = 1

    /*@Ignore
    @Test
    fun `Creates a new list`() {
        val newList = NewList("Lista de Teste", 3)

        Given {
            header("Authorization", "Bearer 9f1e3d11-8c18-4cd7-93fc-985c4794cfd9")
            spec(requestSpecification)
            body(Json.encodeToString(newList))
                .log().all()
        } When {
            post("/lists")
        } Then {
            body("id", Matchers.`is`(4))
            statusCode(HttpStatus.SC_CREATED)
        }
    }*/

    /*@Ignore
    @Test
    fun `Get list details`() {
        Given {
            spec(requestSpecification)
            header("Authorization", "Bearer 9f1e3d11-8c18-4cd7-93fc-985c4794cfd9")
                .log().all()
        } When {
            get("/lists/$listId")
        } Then {
            body("id", CoreMatchers.equalTo(1))
            body("name", CoreMatchers.equalTo("Aula de LS"))
            body("boardId", CoreMatchers.equalTo(1))
            statusCode(HttpStatus.SC_OK)
        }
    }*/

    /*@Ignore
    @Test
    fun `Get the cards in a list`() {
        Given {
            spec(requestSpecification)
            header("Authorization", "Bearer 9f1e3d11-8c18-4cd7-93fc-985c4794cfd9")
                .log().all()
        } When {
            get("/lists/$listId/cards")
        } Then {
            statusCode(HttpStatus.SC_OK)
        }
    }*/

}
