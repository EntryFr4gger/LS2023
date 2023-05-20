package pt.isel.ls.tasks.api.routers.users.models

import kotlinx.serialization.Serializable
import pt.isel.ls.tasks.domain.User

@Serializable
data class UserDTO(val id: Int, val name: String?, val email: String?, val token: String?) {
    companion object {
        operator fun invoke(user: User) = UserDTO(user.id, user.name, user.email, null)
        operator fun invoke(id: Int, token: String) = UserDTO(id, null, null, token)
    }
}
