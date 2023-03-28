package pt.isel.ls.tasks.api

import kotlinx.datetime.Clock
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.filter.ClientFilters
import org.http4k.routing.routes
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.slf4j.LoggerFactory
import pt.isel.ls.tasks.api.routers.boards.BoardsRouter
import pt.isel.ls.tasks.api.routers.boards.cards.models.CardsRouter
import pt.isel.ls.tasks.api.routers.boards.lists.models.ListsRouter
import pt.isel.ls.tasks.api.routers.users.models.UsersRouter
import pt.isel.ls.tasks.services.Services



fun AppAPI() {
    val app = routes(
        UsersRouter.routes(services),
        BoardsRouter.routes(services),
        ListsRouter.routes(services),
        CardsRouter.routes(services),
    )

    val jettyServer = app.asServer(Jetty(9000)).start()
    logger.info("server started listening")

    readln()
    jettyServer.stop()

    logger.info("leaving Main")
}
