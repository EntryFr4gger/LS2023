package pt.isel.ls.tasks.api.routers.boards.models

import kotlinx.serialization.Serializable
import pt.isel.ls.tasks.api.routers.boards.lists.models.ListInfo

@Serializable
data class Board(val id:Int, val name: String, val description: String, val lists: List<ListInfo>)