package pt.isel.ls.tasks.api.routers.lists.models

import kotlinx.serialization.Serializable

@Serializable
data class BoardListsDTO(val boards: List<ListDTO>){
    companion object {
        operator fun invoke(lists: List<pt.isel.ls.tasks.domain.List>): BoardListsDTO =
            BoardListsDTO(lists.fold(emptyList()) { acc, list ->
                acc + ListDTO(list)
            })
    }
}
