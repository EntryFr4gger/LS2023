package pt.isel.ls.tasks.domain

import kotlinx.datetime.LocalDate
import org.eclipse.jetty.util.preventers.AWTLeakPreventer
import pt.isel.ls.tasks.services.utils.isValidId
import java.security.DrbgParameters.NextBytes

/**
 * Represents the Boards table in the database.
 *
 * @property id card unique identifier.
 * @property name the task name.
 * @property description the task description.
 * @property dueDate the task's conclusion date.
 * @property boardId board unique identifier.
 * @property listId list unique identifier.
 * */
data class Card(
    val id: Int,
    val name: String,
    val description: String,
    val dueDate: LocalDate?,
    val cix: Int?,
    val boardId: Int,
    val listId: Int?
) {
    companion object {
        private val nameLength = 2..60
        private val descriptionLength = 1..1000

        fun isValidName(name: String) = name.length in nameLength

        fun isValidDescription(description: String) = description.length in descriptionLength
    }

    init {
        require(!isValidId(id)) { "Invalid card id" }
        require(isValidName(name)) { "Invalid card name" }
        require(isValidDescription(description)) { "Invalid card description" }
        require(!isValidId(boardId)) { "Invalid board id" }
        listId?.let { require(!isValidId(it)) { "Invalid list id" } }
    }
}
