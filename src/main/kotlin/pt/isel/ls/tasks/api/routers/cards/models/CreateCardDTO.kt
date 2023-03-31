package pt.isel.ls.tasks.api.routers.cards.models

import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDate

@Serializable
data class CreateCardDTO(val name: String, val description: String, val dueDate: LocalDate? = null, val listId: Int, val boardId: Int)