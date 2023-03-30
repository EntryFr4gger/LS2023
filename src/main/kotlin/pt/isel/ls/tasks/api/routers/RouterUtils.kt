package pt.isel.ls.tasks.api.routers

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.path
import java.lang.Exception

fun jsonResponse(status: Status, jsonObj: Any) = Response(status)
    .header("content-type", "application/json")
    .body(Json.encodeToString(jsonObj))

fun Request.pathOrThrow(string: String): String {
    return this.path(string) ?: throw IllegalArgumentException("Argument $string not found in path")
}

inline fun errorCatcher(code: () -> Response): Response {
    return try {
        code()
    } catch (error: Exception) {
        Response(Status.OK)
    }
}
