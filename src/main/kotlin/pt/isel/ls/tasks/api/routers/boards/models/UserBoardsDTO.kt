package pt.isel.ls.tasks.api.routers.boards.models

import kotlinx.serialization.Serializable

@Serializable
data class UserBoardsDTO(val boards: List<BoardDTO>) {
    companion object {
        operator fun invoke(boards: List<pt.isel.ls.tasks.domain.Board>): UserBoardsDTO =
            UserBoardsDTO(
                boards.fold(emptyList()) { acc, board ->
                    acc + BoardDTO(board)
                }
            )
    }
}
