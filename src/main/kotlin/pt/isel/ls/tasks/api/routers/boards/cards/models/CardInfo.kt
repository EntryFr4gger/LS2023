package pt.isel.ls.tasks.api.routers.boards.cards.models

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class CardInfo(val id: Int, val name: String, val description:String, val creationDate: String, val dueDate: String)