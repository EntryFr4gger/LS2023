package pt.isel.ls.tasks.api.routers.lists.models

import kotlinx.serialization.Serializable
import pt.isel.ls.tasks.services.modules.boards.response.ListDetailsResponse
import pt.isel.ls.tasks.domain.List as _List

@Serializable
data class ListDTO(val id: Int, val name: String?, val boardId: Int?, val cards: ListCardsDTO?) {
    companion object {
        operator fun invoke(list: _List) =
            ListDTO(list.id, list.name, list.boardId, null)

        operator fun invoke(listRes: ListDetailsResponse) =
            ListDTO(
                listRes.list.id,
                listRes.list.name,
                listRes.list.boardId,
                listRes.cards?.let { ListCardsDTO(it) }
            )

        operator fun invoke(id: Int) = ListDTO(id, null, null, null)
    }
}
