package pt.isel.ls.tasks.api.routers.users.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginUserReturnDTO(val id: String, val token: String)
