package pt.isel.ls.tasks.api.routers.users

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.CREATED
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.routes
import pt.isel.ls.tasks.api.routers.*
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
        ("users/{user_id}" bind Method.GET to ::getUsers).withFilter(BearerTokenFilter()),
    )

    /**
     * Creates a new user
     * @param request HTTP request that contains a JSON body with a name and an email
     * @return HTTP response contains a token and an id for the new user
     */
    private fun postUser(request: Request): Response = errorCatcher {
        val user = Json.decodeFromString<CreateUserDTO>(request.bodyString())
        services.createNewUser(user.name, user.email)
        return jsonResponse(CREATED, UserCreationReturnDTO(1, "jcansda-1231"))
    }

    /**
     * Get the details of a user
     * requires authentication !NOT IMPLEMENTED
     * @param request HTTP request that has a user_id in its path
     */
    fun getUsers(request: Request): Response = errorCatcher {
        val userId = request.pathOrThrow("user_id").toInt()
        val user = services.getUserDetails(userId).let { UserInfoDTO(it.id, it.name, it.email) }
        jsonResponse(OK, user)
    }
}
