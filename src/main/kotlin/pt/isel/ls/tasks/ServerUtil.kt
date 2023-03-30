package pt.isel.ls.tasks

import org.slf4j.Logger
fun Logger.logRequest(request: org.http4k.core.Request) {
    this.info(
        "incoming request: method={}, uri={}, content-type={} accept={}",
        request.method,
        request.uri,
        request.header("content-type"),
        request.header("accept")
    )
}
