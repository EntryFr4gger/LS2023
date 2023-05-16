package pt.isel.ls.tasks.services.modules.boards.response

import pt.isel.ls.tasks.domain.List

/**
 * Create a board details response
 * */
data class BoardDetailsResponse(
    val id: Int,
    val name: String,
    val description: String,
    val lists: kotlin.collections.List<List>?
)
