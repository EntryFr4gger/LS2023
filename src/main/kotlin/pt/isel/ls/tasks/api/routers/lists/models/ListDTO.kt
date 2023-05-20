package pt.isel.ls.tasks.api.routers.lists.models

import kotlinx.serialization.Serializable

@Serializable
data class ListDTO(val id: Int, val name: String?, val boardId: Int?) {
    companion object {
        operator fun invoke(list: pt.isel.ls.tasks.domain.List) = ListDTO(list.id, list.name, list.boardId)
        operator fun invoke(id: Int) = ListDTO(id, null, null)
    }
}
