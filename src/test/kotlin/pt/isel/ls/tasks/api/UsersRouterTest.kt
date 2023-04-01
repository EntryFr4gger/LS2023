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

class UsersRouterTest : BaseTest() {

    @Test
    fun `Creates a new user`() {
        val newUser = NewUser("Manuel Maria", "tes23t@gmail.com")

        Given {
            header("Authorization", "Bearer 9f1e3d11-8c18-4cd7-93fc-985c4794cfd9")
            spec(requestSpecification)
            body(Json.encodeToString(newUser))
                .log().all()
        } When {
            post("/users")
        } Then {
            body("id", Matchers.`is`(5))
            statusCode(HttpStatus.SC_CREATED)
        }
    }

    @Test
    fun `Get User details`() {
        Given {
            spec(requestSpecification)
            header("Authorization", "Bearer 9f1e3d11-8c18-4cd7-93fc-985c4794cfd9")
                .log().all()
        } When {
            get("/users/3")
        } Then {
            body("id", equalTo(3))
            body("name", equalTo("Godofredo"))
            body("email", equalTo("Godofredo@outlook.pt"))
            statusCode(HttpStatus.SC_OK)
        }
    }

    @Test
    fun `Get the all user boards available`() {
        Given {
            spec(requestSpecification)
            header("Authorization", "Bearer 9f1e3d11-8c18-4cd7-93fc-985c4794cfd9")
                .log().all()
        } When {
            get("/users/1/boards")
        } Then {
            statusCode(HttpStatus.SC_OK)
        }
    }

    @Serializable
    data class NewUser(@Required val name: String, @Required val email: String)
}
