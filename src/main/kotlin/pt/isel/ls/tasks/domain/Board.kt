package pt.isel.ls.tasks.domain

import pt.isel.ls.tasks.services.utils.isValidId

/**
 * Represents the Boards table in the database.
 *
 * @property id board unique identifier.
 * @property name unique name for the board.
 * @property description board description.
 * */
data class Board(
    val id: Int,
    val name: String,
    val description: String
) {
    companion object {
        private val nameLength = 2..30
        private val descriptionLength = 1..1000

        fun isValidName(name: String) = name.length in nameLength

        fun isValidDescription(description: String) = description.length in descriptionLength
    }

    init {
        require(!isValidId(id)) { "Invalid board id" }
        require(isValidName(name)) { "Invalid board name" }
        require(isValidDescription(description)) { "Invalid board description" }
    }
}
