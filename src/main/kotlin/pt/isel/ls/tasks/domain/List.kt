package pt.isel.ls.tasks.domain

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
}
