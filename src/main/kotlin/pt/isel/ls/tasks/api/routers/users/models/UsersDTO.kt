package pt.isel.ls.tasks.api.routers.users.models

import kotlinx.serialization.Serializable
import pt.isel.ls.tasks.domain.User

@Serializable
data class UsersDTO(val users: List<UserDTO>) {
    companion object {
        operator fun invoke(users: List<User>): UsersDTO =
            UsersDTO(
                users.fold(emptyList()) { acc, user ->
                    acc + UserDTO(user)
                }
            )
    }
}
