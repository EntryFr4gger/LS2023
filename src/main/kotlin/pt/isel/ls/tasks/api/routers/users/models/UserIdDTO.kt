package pt.isel.ls.tasks.api.routers.users.models

import kotlinx.serialization.Serializable
import pt.isel.ls.tasks.domain.User

@Serializable
data class UserIdDTO(val id: Int)
