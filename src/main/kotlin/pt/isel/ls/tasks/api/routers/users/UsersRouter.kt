package pt.isel.ls.tasks.api.routers.users

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.CREATED
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.routes
import pt.isel.ls.tasks.api.routers.TasksRouter
import pt.isel.ls.tasks.api.routers.users.models.CreateUserDTO
import pt.isel.ls.tasks.api.routers.users.models.UserBoardsDTO
import pt.isel.ls.tasks.api.routers.users.models.UserCreationReturnDTO
import pt.isel.ls.tasks.api.routers.users.models.UserInfoDTO
import pt.isel.ls.tasks.api.utils.TokenUtil
import pt.isel.ls.tasks.api.utils.errorCatcher
import pt.isel.ls.tasks.api.utils.hasOrThrow
import pt.isel.ls.tasks.api.utils.pathOrThrow
import pt.isel.ls.tasks.services.modules.users.UsersServices

class UsersRouter(private val services: UsersServices, private val tokenHandeler: TokenUtil) : TasksRouter {
    companion object {
        fun routes(services: UsersServices, tokenHandeler: TokenUtil) = UsersRouter(services, tokenHandeler).routes
    }

    override val routes = routes(
        "users" bind Method.POST to ::postUser,
        "users/{user_id}" bind Method.GET to ::getUsers,
        ("users/{user_id}/boards" bind Method.GET to ::getUserBoards).withFilter(tokenHandeler::filter)
    )

    /**
     * Creates a new user
     * @param request HTTP request that contains a JSON body with a name and an email
     * @return HTTP response contains a JSON body with an id and a token for the new user
     */
    private fun postUser(request: Request): Response = errorCatcher {
        val user = Json.decodeFromString<CreateUserDTO>(request.bodyString())
        val userCreateInfo = services.createNewUser(user.name, user.email)
        return Response(CREATED)
            .header("content-type", "application/json")
            .body(Json.encodeToString(UserCreationReturnDTO(userCreateInfo.id, userCreateInfo.token)))
    }

    /**
     * Get the details of a user
     * requires authorization ?
     * @param request HTTP request that has a user_id in its path
     * @return HTTP response contains a JSON body with an id, name and email of a user
     */
    private fun getUsers(request: Request): Response = errorCatcher {
        val userId: Int = request.pathOrThrow("user_id").toInt() //
        val user = services.getUserDetails(userId)
        return Response(OK)
            .header("content-type", "application/json")
            .body(Json.encodeToString(UserInfoDTO(user)))
    }

    /**
     * Get the list with all user available boards
     * requires authentication
     * @param request HTTP request
     * @return HTTP response that contains the list of all user boards in the json body
     */
    private fun getUserBoards(request: Request): Response = errorCatcher {
        val requestId = tokenHandeler.context[request].hasOrThrow("user_id")
        val boards = services.getUserBoards(requestId)
        return Response(OK)
            .header("content-type", "application/json")
            .body(Json.encodeToString(UserBoardsDTO(boards)))
    }
}
