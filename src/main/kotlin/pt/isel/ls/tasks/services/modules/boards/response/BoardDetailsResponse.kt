package pt.isel.ls.tasks.services.modules.boards.response

import pt.isel.ls.tasks.domain.Board
import pt.isel.ls.tasks.domain.List

/**
 * Create a board details response
 * */
data class BoardDetailsResponse(
    val board: Board,
    val lists: kotlin.collections.List<List>?
)
