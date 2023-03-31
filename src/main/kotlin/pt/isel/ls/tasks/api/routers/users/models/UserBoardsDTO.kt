package pt.isel.ls.tasks.api.routers.users.models

import kotlinx.serialization.Serializable
import pt.isel.ls.tasks.api.routers.boards.models.BoardDTO

@Serializable
data class UserBoardsDTO(val boards: List<BoardDTO>) {
    companion object {
        operator fun invoke(boards: List<pt.isel.ls.tasks.domain.Board>): UserBoardsDTO =
            UserBoardsDTO(
                boards.fold(emptyList()) { acc, board ->
                    acc + BoardDTO(board)
                },
            )
    }
}
