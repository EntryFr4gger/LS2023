package pt.isel.ls.tasks.api

import org.http4k.core.RequestContexts
import org.http4k.core.then
import org.http4k.filter.CorsPolicy.Companion.UnsafeGlobalPermissive
import org.http4k.filter.ServerFilters
import org.http4k.routing.ResourceLoader
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.routes
import org.http4k.routing.singlePageApp
import org.slf4j.Logger
import pt.isel.ls.tasks.api.routers.boards.BoardsRouter
import pt.isel.ls.tasks.api.routers.cards.CardsRouter
import pt.isel.ls.tasks.api.routers.lists.ListsRouter
import pt.isel.ls.tasks.api.routers.users.UsersRouter
import pt.isel.ls.tasks.api.utils.LoggerUtil
import pt.isel.ls.tasks.api.utils.TokenUtil
import pt.isel.ls.tasks.services.TaskServices

/**
 * HTTP API for handling tasks.
 *
 * @param services The task services instance for task-related operations.
 */
class TasksAPI(services: TaskServices) {
    companion object {
        /**
         * Factory function to create an instance of TasksAPI with routes and filters.
         *
         * @param services The task services instance for task-related operations.
         * @param logger The logger instance for logging request details.
         *
         * @return A RoutingHttpHandler instance representing the configured API.
         */
        operator fun invoke(services: TaskServices, logger: Logger): RoutingHttpHandler {
            return TasksAPI(services)
                .getRoutes()
                .withFilter(LoggerUtil(logger))
                .withFilter(ServerFilters.Cors(UnsafeGlobalPermissive))
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
                CardsRouter.routes(services.cards, tokenHandeler),
                singlePageApp(ResourceLoader.Directory("SPA/static-content"))
            )
        )

    /**
     * Get the configured routes for the TasksAPI.
     *
     * @return A RoutingHttpHandler representing the configured routes.
     */
    fun getRoutes(): RoutingHttpHandler = routes
}
