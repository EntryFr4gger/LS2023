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
        Responde(Status.INTERNAL_SERVER_ERROR,ErrorDTO("Internal Server Error", error))
    }

@Serializable
data class ErrorDTO(val message: String, val error: String) {
    companion object {
        operator fun invoke(message: String, error: Exception) =
            ErrorDTO(message, error.message ?: "No Message")
    }
}

@OptIn(ExperimentalSerializationApi::class)
val format = Json { explicitNulls = false }
inline fun <reified T> Responde(status: Status, dto: T): Response {
    return Response(status)
        .header("content-type", "application/json")
        .body(format.encodeToString(dto))
}
