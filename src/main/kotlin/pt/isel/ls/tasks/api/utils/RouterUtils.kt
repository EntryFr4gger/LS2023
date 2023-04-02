package pt.isel.ls.tasks.api.utils

import org.http4k.core.Response
import org.http4k.core.Status

inline fun errorCatcher(code: () -> Response): Response {
    return try {
        code()
    } catch (error: Exception) {
        println(error)
        Response(Status.INTERNAL_SERVER_ERROR)
    }
}
