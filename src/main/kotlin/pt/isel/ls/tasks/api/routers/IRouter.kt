package pt.isel.ls.tasks.api.routers

import org.http4k.routing.RoutingHttpHandler

interface IRouter {
    val routes: RoutingHttpHandler;
}