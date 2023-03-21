package pt.isel.ls.tasks.api.routers.users.models

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
import pt.isel.ls.tasks.api.routers.MockData.Companion.Users
import pt.isel.ls.tasks.services.Services


class UsersRouter(private val services: Services):IRouter {
    companion object{
        fun routes(services: Services) = UsersRouter(services).routes
    }
    override val routes = routes(
        "users" bind Method.POST to ::postUser,
        "users/{user_id}" bind Method.GET to ::getUsers,
    )

    fun postUser(request: Request): Response {
        logRequest(request)
        val std = Json.decodeFromString<RequestPostUser>(request.bodyString())
        Users.add(ReturnGetUser(Users.last().id+10,std.name,std.email)) //rem
        return Response(Status.CREATED)
            .header("content-type", "application/json")
            .body(Json.encodeToString(ReturnPostUser(std)))
    }

    fun getUsers(request: Request): Response {
        logRequest(request)
        val user_id = request.path("user_id")?.toInt() ?: return Response(Status.BAD_REQUEST)
        val user = services.getUser(user_id)
        return Response(Status.OK)
            .header("content-type", "application/json")
            .body(Json.encodeToString(Users.find { user -> user.id==user_id }))
    }

}