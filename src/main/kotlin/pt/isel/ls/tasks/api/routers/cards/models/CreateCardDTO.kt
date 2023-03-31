package pt.isel.ls.tasks.api.routers.cards.models

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class CreateCardDTO(val name: String, val description: String, val dueDate: LocalDate? = null, val listId: Int, val boardId: Int)
