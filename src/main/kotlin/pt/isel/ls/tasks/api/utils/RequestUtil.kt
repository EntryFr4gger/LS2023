package pt.isel.ls.tasks.api.utils

import org.http4k.core.Request
import org.http4k.core.RequestContext
import org.http4k.routing.path
import javax.naming.AuthenticationException

/**
 * Retrieves the value of a path parameter or throws an IllegalArgumentException if not found.
 *
 * @param string The name of the path parameter.
 *
 * @return The value of the path parameter.
 * @throws IllegalArgumentException if the path parameter is not found.
 */
fun Request.pathOrThrow(string: String) =
    path(string) ?: throw IllegalArgumentException("Argument $string not found in path")

/**
 * Retrieves the value of a header or throws an AuthenticationException if not found.
 *
 * @param string The name of the header.
 *
 * @return The value of the header.
 * @throws AuthenticationException if the header is not found.
 */
fun Request.headerOrThrow(string: String) =
    header(string) ?: throw AuthenticationException("$string not found in header")

/**
 * Retrieves the value of the "skip" query parameter or returns the provided default value if not found.
 *
 * @param default The default value to return if the "skip" query parameter is not found.
 *
 * @return The value of the "skip" query parameter, or the default value if not found.
 */
fun Request.skipOrDefault(default: Int) = query("skip")?.toInt() ?: default

/**
 * Retrieves the value of the "limit" query parameter or returns the provided default value if not found.
 *
 * @param default The default value to return if the "limit" query parameter is not found.
 *
 * @return The value of the "limit" query parameter, or the default value if not found.
 */
fun Request.limitOrDefault(default: Int) = query("limit")?.toInt() ?: default

/**
 * Retrieves the value associated with the specified ID key from the request context or throws an IllegalStateException if not found.
 *
 * @param id The ID key.
 *
 * @return The value associated with the ID key.
 * @throws IllegalStateException if the ID key is not found in the request context.
 */
fun RequestContext.hasOrThrow(id: String): Int =
    this[id] ?: throw IllegalStateException("id not found in context")
