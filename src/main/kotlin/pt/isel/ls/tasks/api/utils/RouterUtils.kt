package pt.isel.ls.tasks.api.utils

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.http4k.core.Response
import org.http4k.core.Status
import pt.isel.ls.tasks.db.errors.NotFoundException
import pt.isel.ls.tasks.services.errors.ServicesError
import java.sql.SQLException

/**
 * Captures and handles different types of errors that can occur within a code block.
 *
 * @param code The code block to execute.
 *
 * @return The response returned by the code block, or an error response if an exception occurs.
 */
inline fun errorCatcher(code: () -> Response): Response =
    try {
        code()
    } catch (error: SerializationException) {
        Responde(Status.BAD_REQUEST, ErrorDTO("Bad Request", error))
    } catch (error: SQLException) {
        Responde(Status.INTERNAL_SERVER_ERROR, ErrorDTO("Data Base Error", error))
    } catch (error: NotFoundException) {
        Responde(Status.NOT_FOUND, ErrorDTO("Resource Not Found", error))
    } catch (error: ServicesError.AuthenticationException) {
        Responde(Status.UNAUTHORIZED, ErrorDTO("Authentication Not Found", error))
    } catch (error: ServicesError.InvalidArgumentException) {
        Responde(Status.BAD_REQUEST, ErrorDTO("Invalid Arguments", error))
    } catch (error: ServicesError.AuthorizationException) {
        Responde(Status.FORBIDDEN, ErrorDTO("Authorization Error", error))
    } catch (error: ServicesError.AlreadyExistsException) {
        Responde(Status.CONFLICT, ErrorDTO("Resource Already Exists", error))
    } catch (error: Exception) {
        Responde(Status.INTERNAL_SERVER_ERROR, ErrorDTO("Internal Server Error", error))
    }

/**
 * Represents an error response DTO (Data Transfer Object) with a message and error details.
 *
 * @param message The error message.
 * @param error The error details.
 */
@Serializable
data class ErrorDTO(val message: String, val error: String) {
    companion object {
        operator fun invoke(message: String, error: Exception) =
            ErrorDTO(message, error.message ?: "No Message")
    }
}

/**
 * Represents a message response DTO with a message and success status.
 *
 * @param message The message content.
 * @param sucess The success status.
 */
@Serializable
data class MessageDTO(val message: String, val sucess: Boolean)

/**
 * JSON format configuration for serialization.
 */
@OptIn(ExperimentalSerializationApi::class)
val format = Json { explicitNulls = false }

/**
 * Creates a JSON-encoded response with the specified status and DTO body.
 *
 * @param status The HTTP status code.
 * @param dto The DTO object to be serialized.
 *
 * @return A Response instance with the specified status and serialized body.
 */
inline fun <reified T> Responde(status: Status, dto: T): Response {
    return Response(status)
        .header("content-type", "application/json")
        .body(format.encodeToString(dto))
}
