package pt.isel.ls.tasks.api.routers.users

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes
import pt.isel.ls.tasks.api.logRequest
import pt.isel.ls.tasks.api.routers.IRouter
import pt.isel.ls.tasks.api.routers.users.models.CreateUserDTO
import pt.isel.ls.tasks.api.routers.users.models.UserCreationReturnDTO
import pt.isel.ls.tasks.api.routers.users.models.UserInfoDTO
import pt.isel.ls.tasks.services.users.UsersServices

class UsersRouter(private val services: UsersServices) : IRouter {
    companion object {
        fun routes(services: UsersServices) = UsersRouter(services).routes
    }
    override val routes = routes(
        "users" bind Method.POST to ::postUser,
        "users/{user_id}" bind Method.GET to ::getUsers
    )

    // Necessita de retornar o BearerToken e o ID. Best way?
    fun postUser(request: Request): Response {
        logRequest(request)
        return try {
            val user = Json.decodeFromString<CreateUserDTO>(request.bodyString())
            services.createNewUser(user.name, user.email)
            Response(Status.CREATED)
                .header("content-type", "application/json")
                .body(Json.encodeToString(UserCreationReturnDTO(1, "jcansda-1231")))
        } catch (ex: Exception) {
            Response(Status.BAD_REQUEST).body("Body format error")
        }
    }

    // Tmb try catch?
    fun getUsers(request: Request): Response {
        logRequest(request)
        val user_id = request.path("user_id")?.toIntOrNull() ?: return Response(Status.BAD_REQUEST).body("ID not valid")
        val user = services.getUserDetails(user_id).let { UserInfoDTO(it.id, it.name, it.email) }
        return Response(Status.OK)
            .header("content-type", "application/json")
            .body(Json.encodeToString(user))
    }
}
