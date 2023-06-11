package pt.isel.ls.tasks.api.utils

import org.http4k.core.HttpHandler
import org.http4k.core.RequestContexts
import pt.isel.ls.tasks.services.modules.token.TokenServices

/**
 * Utility class for handling authorization tokens.
 *
 * @param services The token services instance for token validation and user ID retrieval.
 * @param context The request contexts instance for storing user ID in the request context.
 */
data class TokenUtil(private val services: TokenServices, val context: RequestContexts) {
    /**
     * Filter function that validates the authorization token and extracts the user ID.
     *
     * @param next The next HttpHandler in the chain.
     * @return An HttpHandler that performs the token validation and user ID extraction.
     */
    fun filter(next: HttpHandler): HttpHandler = { request ->
        errorCatcher {
            // Extract the authorization token from the request header
            val token = request.headerOrThrow("Authorization")

            // Retrieve the user ID using the token services
            val id = services.getUserId(token)

            // Store the user ID in the request context for later use
            context[request]["user_id"] = id

            // Call the next HttpHandler in the chain
            next(request)
        }
    }
}
