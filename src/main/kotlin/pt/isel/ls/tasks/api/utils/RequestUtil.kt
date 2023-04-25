package pt.isel.ls.tasks.api.utils

import org.http4k.core.Request
import org.http4k.core.RequestContext
import org.http4k.routing.path
import javax.naming.AuthenticationException

fun Request.pathOrThrow(string: String) =
    path(string) ?: throw IllegalArgumentException("Argument $string not found in path")

fun Request.headerOrThrow(string: String) =
    header(string) ?: throw AuthenticationException("$string not found in header")

fun Request.skipOrDefault(default: Int) = query("skip")?.toInt() ?: default

fun Request.limitOrDefault(default: Int) = query("limit")?.toInt() ?: default

fun RequestContext.hasOrThrow(id: String): Int =
    this[id] ?: throw IllegalStateException("id not found in context")
