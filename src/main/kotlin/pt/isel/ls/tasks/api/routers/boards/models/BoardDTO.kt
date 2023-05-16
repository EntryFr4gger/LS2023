package pt.isel.ls.tasks.api.routers.boards.models

import kotlinx.serialization.Serializable
import pt.isel.ls.tasks.domain.Board
import pt.isel.ls.tasks.services.modules.boards.response.BoardDetailsResponse

@Serializable
data class BoardDTO(val id: Int, val name: String, val description: String, val lists: BoardListsDTO?) {
    companion object {
        operator fun invoke(board: BoardDetailsResponse) =
            BoardDTO(board.id, board.name, board.description, BoardListsDTO(board.lists))
        operator fun invoke(board: Board) =
            BoardDTO(board.id, board.name, board.description, null)
    }
}
