package pt.isel.ls.tasks

import org.http4k.core.*
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import pt.isel.ls.tasks.api.AppAPI
import pt.isel.ls.tasks.db.TasksDataPostgres
import pt.isel.ls.tasks.services.TaskServices

const val PORT = 9000
fun Logger.logRequest(request: Request) {
    this.info(
        "incoming request: method={}, uri={}, content-type={} accept={}",
        request.method,
        request.uri,
        request.header("content-type"),
        request.header("accept"),
    )
}
fun main() {
    val logger = LoggerFactory.getLogger("Tasks API")
    val services = TaskServices(TasksDataPostgres("JDBC_DATABASE_URL"))

    val logRequestFilter = Filter { next ->
        { request ->
            logger.logRequest(request)
            next(request)
        }
    }

    val app = AppAPI(services)
        .getRoutes()
        .withFilter(logRequestFilter)

    val jettyServer = app.asServer(Jetty(9000)).start()
    logger.info("server started listening")

    readln()
    jettyServer.stop()

    logger.info("leaving Main")
}
