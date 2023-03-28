package pt.isel.ls.tasks.api.routers.users.models

import kotlinx.serialization.Serializable

@Serializable
data class UserCreationReturnDTO(val id: Int, val token : String)