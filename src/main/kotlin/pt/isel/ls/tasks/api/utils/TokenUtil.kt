package pt.isel.ls.tasks.api.utils

import org.http4k.core.HttpHandler
import org.http4k.core.RequestContexts
import pt.isel.ls.tasks.services.modules.tokenHandeler.TokenServices

data class TokenUtil(private val services: TokenServices, val context: RequestContexts) {
    fun filter(next: HttpHandler): HttpHandler = { request ->
        errorCatcher {
            val token = request.headerOrThrow("Authorization")
            val id = services.getUserId(token)
            context[request]["user_id"] = id
            next(request)
        }
    }
}
