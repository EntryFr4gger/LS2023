package pt.isel.ls.tasks.api.routers.boards.models

import kotlinx.serialization.Serializable
import pt.isel.ls.tasks.api.routers.users.models.UserInfoDTO
import pt.isel.ls.tasks.domain.User

@Serializable
data class BoardUsersDTO(val users: List<UserInfoDTO>) {
    companion object {
        operator fun invoke(users: List<User>): BoardUsersDTO =
            BoardUsersDTO(
                users.fold(emptyList()) { acc, user ->
                    acc + UserInfoDTO(user)
                }
            )
    }
}
