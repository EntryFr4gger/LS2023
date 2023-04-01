package pt.isel.ls.tasks.api

import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import kotlinx.serialization.Required
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.apache.http.HttpStatus
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.api.core.BaseTest


class BoardsRouterTest:BaseTest() {
    @Test
    fun `Creates a new Board`() {

        val newBoard = NewBoard("TestBoard", "Isto é o board que vai ser usado para testes")

        Given {

            header("Authorization", "Bearer 9f1e3d11-8c18-4cd7-93fc-985c4794cfd9")
            spec(requestSpecification)
            body(Json.encodeToString(newBoard))
                .log().all()

        } When {

            post("/boards")

        } Then {
            body("id", Matchers.`is`(4))
            statusCode(HttpStatus.SC_CREATED)
        }
    }

    @Test
    fun `Add a User to a Board` (){

        Given {

            header("Authorization", "Bearer 9f1e3d11-8c18-4cd7-93fc-985c4794cfd9")
            spec(requestSpecification)
                .log().all()

        } When {

            post("/boards")

        } Then {
            body("id", Matchers.`is`(4))
            statusCode(HttpStatus.SC_CREATED)
        }

    }
    @Test
    fun `Get Board details`() {

        Given {
            spec(requestSpecification)
            header("Authorization", "Bearer 9f1e3d11-8c18-4cd7-93fc-985c4794cfd9")
                .log().all()

        } When {

            get("/boards/3")

        } Then {
            body("id", equalTo(3))
            body("name", equalTo("Limpeza"))
            body("description", equalTo("O que falta limpar cá em casa"))
            statusCode(HttpStatus.SC_OK)
        }
    }


    @Serializable
    data class NewBoard(@Required val name: String, @Required val description: String)

}






