package pt.isel.ls.tasks.api

import org.eclipse.jetty.server.Request
import org.slf4j.Logger
import org.slf4j.LoggerFactory


fun logRequest(logger: Logger,request: org.http4k.core.Request) {
    logger.info(
        "incoming request: method={}, uri={}, content-type={} accept={}",
        request.method,
        request.uri,
        request.header("content-type"),
        request.header("accept"),
    )
}

