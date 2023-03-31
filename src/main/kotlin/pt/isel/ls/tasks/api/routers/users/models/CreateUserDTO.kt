package pt.isel.ls.tasks.api.routers.users.models

import kotlinx.serialization.Serializable
import pt.isel.ls.tasks.api.ISerializable
import pt.isel.ls.tasks.api.routers.boards.models.BoardDTO
import pt.isel.ls.tasks.domain.Board

@Serializable
data class CreateUserDTO(val name: String, val email: String)

