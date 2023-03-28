package pt.isel.ls.tasks.api.routers.boards.lists.models

import kotlinx.serialization.Serializable
import pt.isel.ls.tasks.api.routers.boards.cards.models.CardInfo

@Serializable
data class ListInfo(val id: Int, val name: String, val cards: List<CardInfo>)

