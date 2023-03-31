package pt.isel.ls.tasks.domain

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

/**
 *
 * */
data class Card(
    val id: Int,
    val name: String,
    val description: String,
    val dueDate: LocalDate?,
    val boardId: Int,
    val listId: Int?
) {
    companion object {
        private val nameLength = 2..60
        private val descriptionLength = 1..1000

        fun isValidName(name: String) = name.length in nameLength

        fun isValidDescription(description: String) = description.length in descriptionLength
    }
}
