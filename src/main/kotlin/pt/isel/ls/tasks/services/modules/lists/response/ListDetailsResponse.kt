package pt.isel.ls.tasks.services.modules.boards.response

import pt.isel.ls.tasks.domain.Card
import pt.isel.ls.tasks.domain.List as _List

/**
 * Create a board details response
 * */
data class ListDetailsResponse(
    val list: _List,
    val cards: List<Card>?
)
