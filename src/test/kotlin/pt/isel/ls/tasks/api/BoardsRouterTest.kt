package pt.isel.ls.tasks.api

import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import kotlinx.serialization.Required
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.apache.http.HttpStatus
import org.http4k.core.RequestContexts
import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.api.core.BaseTest
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.services.modules.boards.BoardsServices


class BoardsRouterTest:BaseTest() {
    private val storage = TasksDataStorage()
    private val source = TasksDataMem(storage)
    private val boardsServices = BoardsServices(source)
    val context = RequestContexts()
   // val boardsRouter = BoardsRouter(boardsServices, context)


    @Test
    fun `Get Board details` (){


        Given { spec(requestSpecification)
            header("Authorization", "Bearer 9f1e3d11-8c18-4cd7-93fc-985c4794cfd9")
            .log().all()

        }When {

            get("/boards/3")

        }Then {
           // body("id", equalTo(3))
           // body("name", equalTo( "Limpeza"))
            // body("description", equalTo( "O que falta limpar cá em casa"))
            statusCode(HttpStatus.SC_OK)
        }
    }

    @Test
    fun `Creates a new Board` (){

        val newBoard = NewBoard( "TestBoard", "Isto é o board que vai ser usado para testes")

        Given {

            header("Authorization", "Bearer 9f1e3d11-8c18-4cd7-93fc-985c4794cfd9")
            spec(requestSpecification)
            body(Json.encodeToString(newBoard))
                .log().all()

        }When {

            post("/boards")

        }Then {

            statusCode(HttpStatus.SC_CREATED)
        }

    }

    @Serializable
    data class NewBoard(@Required val name: String, @Required val description: String)

}






