package pt.isel.ls.tasks.api.routers.users

import kotlinx.serialization.json.Json
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.Status.Companion.CREATED
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.routes
import pt.isel.ls.tasks.api.routers.TasksRouter
import pt.isel.ls.tasks.api.routers.boards.models.BoardIdDTO
import pt.isel.ls.tasks.api.routers.users.models.CreateUserDTO
import pt.isel.ls.tasks.api.routers.users.models.LoginUserDTO
import pt.isel.ls.tasks.api.routers.users.models.UserBoardsDTO
import pt.isel.ls.tasks.api.routers.users.models.UserDTO
import pt.isel.ls.tasks.api.routers.users.models.UsersDTO
import pt.isel.ls.tasks.api.utils.Responde
import pt.isel.ls.tasks.api.utils.TokenUtil
import pt.isel.ls.tasks.api.utils.errorCatcher
import pt.isel.ls.tasks.api.utils.hasOrThrow
import pt.isel.ls.tasks.api.utils.limitOrDefault
import pt.isel.ls.tasks.api.utils.pathOrThrow
import pt.isel.ls.tasks.api.utils.skipOrDefault
import pt.isel.ls.tasks.services.modules.users.UsersServices

class UsersRouter(private val services: UsersServices, private val tokenHandeler: TokenUtil) : TasksRouter {
    companion object {
        private const val DEFAULT_SKIP = 0
        private const val DEFAULT_LIMIT = Int.MAX_VALUE

        fun routes(services: UsersServices, tokenHandeler: TokenUtil) = UsersRouter(services, tokenHandeler).routes
    }

    override val routes = routes(
        "users" bind Method.POST to ::postUser,
        "users/login" bind Method.POST to ::loginUser,
        "users/{user_id}" bind Method.GET to ::getUserDetails,
        ("users/{user_id}" bind Method.POST to ::getAllUsers).withFilter(tokenHandeler::filter),
        ("users/{user_id}/boards" bind Method.GET to ::getUserBoards).withFilter(tokenHandeler::filter)
    )

    /**
     * Creates a new user.
     *
     * @param request HTTP request that contains a JSON body with a name and an email
     *
     * @return HTTP response contains a JSON body with an id and a token for the new user
     */
    private fun postUser(request: Request): Response = errorCatcher {
        val user = Json.decodeFromString<CreateUserDTO>(request.bodyString())
        val userCreateInfo = services.createNewUser(user.name, user.email, user.password)
        return Responde(CREATED, UserDTO(userCreateInfo.id, userCreateInfo.token))
    }

    /**
     * Logins a user.
     *
     * @param request HTTP request that contains a JSON body with an email and password
     *
     * @return HTTP response contains a JSON body with an id and a token for the login
     */
    private fun loginUser(request: Request): Response = errorCatcher {
        val user = Json.decodeFromString<LoginUserDTO>(request.bodyString())
        val userLoginInfo = services.loginUser(user.email, user.password)
        return Responde(OK, UserDTO(userLoginInfo.id, userLoginInfo.token))
    }

    /**
     * Get the details of a user.
     * Requires authorization.
     *
     * @param request HTTP request that has a user_id in its path
     *
     * @return HTTP response contains a JSON body with an id, name and email of a user
     */
    private fun getUserDetails(request: Request): Response = errorCatcher {
        val userId = request.pathOrThrow("user_id").toInt()
        val user = services.getUserDetails(userId)
        return Responde(OK, UserDTO(user))
    }

    /**
     * Get the list with all user available boards.
     * Requires authentication.
     *
     * @param request HTTP request
     *
     * @return HTTP response that contains the list of all user boards in the json body
     */
    private fun getUserBoards(request: Request): Response = errorCatcher {
        val requestId = tokenHandeler.context[request].hasOrThrow("user_id")
        val boards =
            services.getUserBoards(
                requestId,
                request.skipOrDefault(DEFAULT_SKIP),
                request.limitOrDefault(DEFAULT_LIMIT)
            )
        return Responde(OK, UserBoardsDTO(boards))
    }

    /**
     * Gets all Users in the database.
     *
     * @param request HTTP request that contains the name to search
     *
     * @return list of Users.
     * */
    private fun getAllUsers(request: Request): Response = errorCatcher {
        val boardId = Json.decodeFromString<BoardIdDTO>(request.bodyString())
        val requestId = tokenHandeler.context[request].hasOrThrow("user_id")
        val users = services.getAllUsers(boardId.id, requestId)
        return Responde(OK, UsersDTO(users))
    }
}
