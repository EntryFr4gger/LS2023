package pt.isel.ls.tasks.api

import org.http4k.core.RequestContexts
import org.http4k.core.then
import org.http4k.filter.ServerFilters
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.routes
import org.slf4j.Logger
import pt.isel.ls.tasks.api.routers.boards.BoardsRouter
import pt.isel.ls.tasks.api.routers.cards.CardsRouter
import pt.isel.ls.tasks.api.routers.lists.ListsRouter
import pt.isel.ls.tasks.api.routers.users.UsersRouter
import pt.isel.ls.tasks.api.utils.LoggerUtil
import pt.isel.ls.tasks.api.utils.TokenUtil
import pt.isel.ls.tasks.services.TaskServices

class TasksAPI(services: TaskServices) {
    companion object {
        operator fun invoke(services: TaskServices, logger: Logger): RoutingHttpHandler {
            return TasksAPI(services)
                .getRoutes()
                .withFilter(LoggerUtil(logger))
        }
    }
    private val context = RequestContexts()
    private val tokenHandeler = TokenUtil(services.tokens, context)
    private val routes = ServerFilters.InitialiseRequestContext(context)
        .then(
            routes(
                UsersRouter.routes(services.users, tokenHandeler),
                BoardsRouter.routes(services.boards, tokenHandeler),
                ListsRouter.routes(services.lists, tokenHandeler),
                CardsRouter.routes(services.cards, tokenHandeler)
            )
        )
    fun getRoutes(): RoutingHttpHandler = routes
}
