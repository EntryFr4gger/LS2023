package pt.isel.ls.tasks.api.utils

import org.http4k.core.Request
import org.http4k.core.RequestContext
import org.http4k.routing.path
import java.lang.IllegalStateException
import javax.naming.AuthenticationException

fun Request.pathOrThrow(string: String): String {
    return this.path(string) ?: throw IllegalArgumentException("Argument $string not found in path")
}
fun Request.headerOrThrow(string: String): String {
    return this.header(string) ?: throw AuthenticationException("$string not found in header")
}
fun RequestContext.hasOrThrow(id: String): Int {
    return this[id] ?: throw IllegalStateException("id not found in context")
}
