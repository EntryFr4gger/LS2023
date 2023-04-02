package pt.isel.ls.tasks.api

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
import pt.isel.ls.tasks.api.core.BaseTest


class CardsRouterTest : BaseTest() {
    private val cardId = 1


    @Test
    fun `Creates a new list`() {
        val newCard = NewCard("CardTest", "Teste num é mesmo", 1, 1)

        Given {
            header("Authorization", "Bearer 9f1e3d11-8c18-4cd7-93fc-985c4794cfd9")
            spec(requestSpecification)
            body(Json.encodeToString(newCard))
                .log().all()
        } When {
            post("/cards")
        } Then {
            body("id", Matchers.`is`(5))
            statusCode(HttpStatus.SC_CREATED)
        }
    }

    @Test
    fun `Get card details`() {
        Given {
            spec(requestSpecification)
            header("Authorization", "Bearer 9f1e3d11-8c18-4cd7-93fc-985c4794cfd9")
                .log().all()
        } When {
            get("/cards/$cardId")
        } Then {
            body("id", CoreMatchers.equalTo(1))
            body("name", CoreMatchers.equalTo("Phase 1"))
            body("description", CoreMatchers.equalTo("Entrega da parte 1 do trabalho de LS"))
            body("dueDate", CoreMatchers.equalTo("2023-04-02"))
            statusCode(HttpStatus.SC_OK)
        }
    }


    @Test
    fun `Moves a Card to a new List`() {
        val listToMove = NewList(2)
        Given {
            spec(requestSpecification)
            body(Json.encodeToString(listToMove))
            header("Authorization", "Bearer 9f1e3d11-8c18-4cd7-93fc-985c4794cfd9")
                .log().all()
        } When {
            put("/cards/$cardId")
        } Then {
            statusCode(HttpStatus.SC_OK)
        }
    }

    @Serializable
    data class NewCard(
        @Required val name: String,
        @Required val description: String,
        @Required val boardId: Int,
        @Required val listId: Int
    )

    @Serializable
    data class NewList(@Required val lid: Int)
}
