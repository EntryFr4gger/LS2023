package pt.isel.ls.tasks.api.routers

import org.http4k.routing.RoutingHttpHandler

/**
 * Represents a router for handling tasks-related routes.
 */
interface TasksRouter {
    val routes: RoutingHttpHandler
}
