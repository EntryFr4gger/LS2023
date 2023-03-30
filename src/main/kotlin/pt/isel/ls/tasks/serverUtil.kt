package pt.isel.ls.tasks

import org.http4k.core.Filter
import org.http4k.core.HttpHandler
import org.http4k.core.Response
import org.http4k.core.Status
import org.slf4j.Logger
fun Logger.logRequest(request: org.http4k.core.Request) {
    this.info(
        "incoming request: method={}, uri={}, content-type={} accept={}",
        request.method,
        request.uri,
        request.header("content-type"),
        request.header("accept"),
    )
}

class Logger(private val logger: Logger) : Filter {
    override operator fun invoke(next: HttpHandler): HttpHandler = { request ->
        logger.logRequest(request)
        next(request)
    }
}


