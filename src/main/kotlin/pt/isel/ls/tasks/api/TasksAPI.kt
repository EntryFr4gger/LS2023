package pt.isel.ls.tasks.api

import org.http4k.core.RequestContexts
import org.http4k.core.then
import org.http4k.filter.ServerFilters
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.routes
import pt.isel.ls.tasks.api.routers.boards.BoardsRouter
import pt.isel.ls.tasks.api.routers.cards.CardsRouter
import pt.isel.ls.tasks.api.routers.lists.ListsRouter
import pt.isel.ls.tasks.api.routers.users.UsersRouter
import pt.isel.ls.tasks.services.TaskServices


class TasksAPI(services: TaskServices) {


    val context = RequestContexts()

    private val routes =ServerFilters.InitialiseRequestContext(context)
        .then(routes(
            UsersRouter.routes(services.users,context),
            BoardsRouter.routes(services.boards,context),
            ListsRouter.routes(services.lists,context),
            CardsRouter.routes(services.cards,context)
        ))


    fun getRoutes(): RoutingHttpHandler = routes
}
