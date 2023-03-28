package pt.isel.ls.tasks.api.routers.boards.models

import kotlinx.serialization.Serializable

@Serializable
data class CreateBoardDTO(val name: String, val description: String)