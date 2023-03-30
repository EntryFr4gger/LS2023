package pt.isel.ls.tasks.domain

/**
 *
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
