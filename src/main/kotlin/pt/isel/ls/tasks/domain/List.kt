package pt.isel.ls.tasks.domain

import pt.isel.ls.tasks.services.utils.isValidId

/**
 * Represents the Boards table in the database.
 *
 * @property id list unique identifier.
 * @property name list name.
 * @property boardId board unique identifier.
 * */
data class List(
    val id: Int,
    val name: String,
    val boardId: Int
) {
    companion object {
        private val nameLength = 2..60

        fun isValidName(name: String) = name.length in nameLength
    }

    init {
        require(!isValidId(id)) { "Invalid list id" }
        require(isValidName(name)) { "Invalid list name" }
        require(!isValidId(boardId)) { "Invalid board id" }
    }
}
