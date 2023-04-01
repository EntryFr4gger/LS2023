package pt.isel.ls.tasks.api.utils

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.http4k.core.Response
import org.http4k.core.Status
import pt.isel.ls.tasks.api.ISerializable

// Need to make this one serializable
fun jsonResponse(status: Status, jsonObj: ISerializable) = Response(status)
    .header("content-type", "application/json")
    .body(Json.encodeToString(jsonObj))

inline fun errorCatcher(code: () -> Response): Response {
    return try {
        code()
    } catch (error: Exception) {
        println(error)
        Response(Status.INTERNAL_SERVER_ERROR)
    }
}
