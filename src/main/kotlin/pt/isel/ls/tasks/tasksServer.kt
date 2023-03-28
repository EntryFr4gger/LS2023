package pt.isel.ls.tasks

import org.http4k.core.Request
import org.http4k.routing.routes
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.slf4j.LoggerFactory
import pt.isel.ls.tasks.api.routers.boards.BoardsRouter
import pt.isel.ls.tasks.api.routers.boards.cards.models.CardsRouter
import pt.isel.ls.tasks.api.routers.boards.lists.models.ListsRouter
import pt.isel.ls.tasks.api.routers.users.models.UsersRouter
import pt.isel.ls.tasks.services.Services

const val PORT = 9000

fun main() {
    val logger = LoggerFactory.getLogger("Tasks API")
    val services = Services()

    val app = routes(
        UsersRouter.routes(services),
        BoardsRouter.routes(services),
        ListsRouter.routes(services),
        CardsRouter.routes(services),
    )

    val jettyServer = app.asServer(Jetty(PORT)).start()
    logger.info("server started listening")

    readln()
    jettyServer.stop()

    logger.info("leaving Main")
}
