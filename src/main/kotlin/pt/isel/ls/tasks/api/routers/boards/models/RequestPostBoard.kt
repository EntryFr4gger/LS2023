package pt.isel.ls.tasks.api.routers.boards.models

import kotlinx.serialization.Serializable

@Serializable
data class CreateBoard(val name: String, val description: String)