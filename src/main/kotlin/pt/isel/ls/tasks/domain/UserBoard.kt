package pt.isel.ls.tasks.domain

/**
 * Represents the UserBoard table in the database.
 *
 * @property userId user unique identifier.
 * @property boardId board unique identifier.
 * */
data class UserBoard(
    val userId: Int,
    val boardId: Int
)
