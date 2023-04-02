package pt.isel.ls.tasks.project

class BoardsRouterTest : InstanceProjectTest() {
    private val boardId = 3
    private val userId = 1

    /*@Ignore
    @Test
    fun `Creates a new Board`() {
        val newBoard = NewBoard("TestBoard", "Isto é o board que vai ser usado para testes")

        Given {
            header("Authorization", "Bearer 9f1e3d11-8c18-4cd7-93fc-985c4794cfd9")
            body(Json.encodeToString(newBoard))
                .log().all()
        } When {
            post("/boards")
        } Then {
            body("id", Matchers.`is`(4))
            statusCode(HttpStatus.SC_CREATED)
        }
    }*/

    /*@Ignore
    @Test
    fun `Add a User to a Board`() {
        Given {
            header("Authorization", "Bearer 9f1e3d11-8c18-4cd7-93fc-985c4794cfd9")
            spec(requestSpecification)
                .log().all()
        } When {
            post("/boards/$boardId/users/$userId")
        } Then {
            statusCode(HttpStatus.SC_OK)
        }
    }*/

    /*@Ignore
    @Test
    fun `Get Board details`() {
        Given {
            spec(requestSpecification)
            header("Authorization", "Bearer 9f1e3d11-8c18-4cd7-93fc-985c4794cfd9")
                .log().all()
        } When {
            get("/boards/$boardId")
        } Then {
            body("id", equalTo(3))
            body("name", equalTo("Limpeza"))
            body("description", equalTo("O que falta limpar cá em casa"))
            statusCode(HttpStatus.SC_OK)
        }
    }*/

   /* @Ignore
    @Test
    fun `Get the Lists of a Board`() {
        *//*val expectResponse = listOf(
            TestList(1, "Aula de LS", 1),
            TestList(2, "Aula de LAE", 1)
        )*//*
        Given {
            spec(requestSpecification)
            header("Authorization", "Bearer 9f1e3d11-8c18-4cd7-93fc-985c4794cfd9")
                .log().all()
        } When {
            get("/boards/$boardId/lists")
        } Then {
            statusCode(HttpStatus.SC_OK)
        }
    }*/

    /*@Serializable
    data class TestList(@Required val id: Int, @Required val name: String, @Required val boardId: Int)
*/
}
