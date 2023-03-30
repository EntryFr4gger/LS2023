package pt.isel.ls.tasks.api

import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.routes
import pt.isel.ls.tasks.api.routers.boards.BoardsRouter
import pt.isel.ls.tasks.api.routers.cards.CardsRouter
import pt.isel.ls.tasks.api.routers.lists.ListsRouter
import pt.isel.ls.tasks.api.routers.users.UsersRouter
import pt.isel.ls.tasks.services.TaskServices

class AppAPI(services: TaskServices) {

    private val routes = routes(
        UsersRouter.routes(services.users),
        BoardsRouter.routes(services.boards),
        ListsRouter.routes(services.lists),
        CardsRouter.routes(services.cards)
    )

    fun getRoutes(): RoutingHttpHandler = routes
}
