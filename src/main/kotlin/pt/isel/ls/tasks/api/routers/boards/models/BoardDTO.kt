package pt.isel.ls.tasks.api.routers.boards.models

import kotlinx.serialization.Serializable
import pt.isel.ls.tasks.domain.Board

@Serializable
data class BoardDTO(val id:Int, val name: String, val description: String){
    companion object{
        operator fun invoke(board: Board) = BoardDTO(board.id,board.name,board.description)
    }
}