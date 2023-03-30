package pt.isel.ls.tasks.domain

/**
 *
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
}
