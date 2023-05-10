package pt.isel.ls.tasks.api.utils

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.http4k.core.Response
import org.http4k.core.Status
import pt.isel.ls.tasks.db.errors.NotFoundException
import pt.isel.ls.tasks.services.errors.ServicesError
import java.sql.SQLException

inline fun errorCatcher(code: () -> Response): Response {
    val json = Json { encodeDefaults = false }

    return try {
        code()
    } catch (error: SerializationException) {
        json.response(Status.BAD_REQUEST, "Bad Request", error.message)
    } catch (error: SQLException) {
        json.response(Status.INTERNAL_SERVER_ERROR, "Data Base Error", error.message)
    } catch (error: NotFoundException) {
        json.response(Status.NOT_FOUND, "Resource Not Found", error.message)
    } catch (error: ServicesError.AuthenticationException) {
        json.response(Status.UNAUTHORIZED, "Authentication Not Found", error.message)
    } catch (error: ServicesError.InvalidArgumentException) {
        json.response(Status.BAD_REQUEST, "Invalid Arguments", error.message)
    } catch (error: ServicesError.AuthorizationException) {
        json.response(Status.FORBIDDEN, "Authorization Error", error.message)
    } catch (error: ServicesError.AlreadyExistsException) {
        json.response(Status.CONFLICT, "Resource Already Exists", error.message)
    } catch (error: Exception) {
        println(error)
        Response(Status.INTERNAL_SERVER_ERROR)
    }
}

@Serializable
data class ErrorDTO(val message: String, val error: String)

fun Json.response(status: Status, message: String, error: String?) =
    Response(status)
        .header("Content-Type", "application/json")
        .body(encodeToString(ErrorDTO(message, error ?: "No Message")))
