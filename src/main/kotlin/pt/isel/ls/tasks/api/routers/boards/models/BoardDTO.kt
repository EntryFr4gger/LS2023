package pt.isel.ls.tasks.api.routers.boards.models

import kotlinx.serialization.Serializable
import pt.isel.ls.tasks.domain.Board
import pt.isel.ls.tasks.services.modules.boards.response.BoardDetailsResponse

@Serializable
data class BoardDTO(val id: Int, val name: String?, val description: String?, val lists: BoardListsDTO?) {
    companion object {
        operator fun invoke(boardRes: BoardDetailsResponse) =
            BoardDTO(
                boardRes.board.id,
                boardRes.board.name,
                boardRes.board.description,
                boardRes.lists?.let { BoardListsDTO(it) }
            )

        operator fun invoke(board: Board) =
            BoardDTO(board.id, board.name, board.description, null)

        operator fun invoke(id: Int) =
            BoardDTO(id, null, null, null)
    }
}
