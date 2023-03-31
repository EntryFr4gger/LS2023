package pt.isel.ls.tasks.api.utils

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.http4k.core.* // ktlint-disable no-wildcard-imports
import org.http4k.routing.path
import pt.isel.ls.tasks.api.ISerializable
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.services.TaskServices
import java.lang.Exception
import java.lang.IllegalStateException
import java.util.*
import javax.naming.AuthenticationException

// Need to make this one serializable
fun jsonResponse(status: Status, jsonObj: ISerializable) = Response(status)
    .header("content-type", "application/json")
    .body(Json.encodeToString(jsonObj))

fun Request.pathOrThrow(string: String): String {
    return this.path(string) ?: throw IllegalArgumentException("Argument $string not found in path")
}
fun Request.headerOrThrow(string: String): String {
    return this.header(string) ?: throw AuthenticationException("Authorization not found in header")
}
fun RequestContext.hasOrThrow(id: String): Int {
    return this[id] ?: throw IllegalStateException("id not found in context")
}

inline fun errorCatcher(code: () -> Response): Response {
    return try {
        code()
    } catch (error: Exception) {
        println(error)
        Response(Status.INTERNAL_SERVER_ERROR)
    }
}

class filterToken(private val context: RequestContexts) : Filter {
    val services = TaskServices(TasksDataMem(TasksDataStorage())).tokens // TEM DE SER MUDADO POR AMOR DE DEUS
    override operator fun invoke(next: HttpHandler): HttpHandler = { request ->
        errorCatcher {
            val token = request.headerOrThrow("Authorization")
            val id = services.getId(token)
            context[request]["user_id"] = id
            next(request)
        }
    }
}
