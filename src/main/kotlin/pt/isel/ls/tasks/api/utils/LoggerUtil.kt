package pt.isel.ls.tasks.api.utils // ktlint-disable filename

import org.http4k.core.Filter
import org.http4k.core.HttpHandler
import org.slf4j.Logger

/**
 * Extension function to log an incoming request.
 *
 * @param request The incoming request to be logged.
 */
fun Logger.logRequest(request: org.http4k.core.Request) {
    this.info(
        "incoming request: method={}, uri={}, content-type={} accept={}",
        request.method,
        request.uri,
        request.header("content-type"),
        request.header("accept")
    )
}

/**
 * A utility class that acts as a filter to log HTTP requests.
 *
 * @param logger The logger instance used for logging.
 */
class LoggerUtil(private val logger: Logger) : Filter {
    /**
     * Invokes the filter by logging the request and passing it to the next handler.
     *
     * @param next The next HTTP handler in the chain.
     *
     * @return The resulting HTTP handler.
     */
    override operator fun invoke(next: HttpHandler): HttpHandler = { request ->
        logger.logRequest(request)
        next(request)
    }
}
