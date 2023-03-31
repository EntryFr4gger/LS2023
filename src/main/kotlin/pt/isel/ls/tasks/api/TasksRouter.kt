package pt.isel.ls.tasks.api

import org.http4k.routing.RoutingHttpHandler

interface TasksRouter {
    val routes: RoutingHttpHandler
}
