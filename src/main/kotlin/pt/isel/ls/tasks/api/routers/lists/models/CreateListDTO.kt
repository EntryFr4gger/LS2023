package pt.isel.ls.tasks.api.routers.lists.models

import kotlinx.serialization.Serializable

@Serializable
data class CreateListDTO(val name: String)