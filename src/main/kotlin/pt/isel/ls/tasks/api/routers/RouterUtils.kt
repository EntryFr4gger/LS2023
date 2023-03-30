package pt.isel.ls.tasks.api.routers

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.http4k.core.* // ktlint-disable no-wildcard-imports
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

class BearerTokenFilter : Filter {
    override operator fun invoke(next: HttpHandler): HttpHandler = { request ->
        if (request.header("Authorization")?.startsWith("Bearer ") != true) {
            Response(Status.UNAUTHORIZED).body("Invalid or missing Bearer token")
        } else {
            next(request)
        }
    }
}
