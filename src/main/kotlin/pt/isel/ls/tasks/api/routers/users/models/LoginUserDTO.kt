package pt.isel.ls.tasks.api.routers.users.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginUserDTO(val email: String, val password: String)
