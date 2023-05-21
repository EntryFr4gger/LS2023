package pt.isel.ls.tasks.api.routers.cards.models

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import pt.isel.ls.tasks.domain.Card

@Serializable
data class CardDTO(
    val id: Int,
    val name: String?,
    val description: String?,
    val boardId: Int?,
    val dueDate: LocalDate? = null,
    val listId: Int? = null,
    val cix: Int? = null
) {
    companion object {

        operator fun invoke(card: Card) =
            CardDTO(card.id, card.name, card.description, card.boardId, card.dueDate, card.listId)
        operator fun invoke(id: Int, listId: Int, cix: Int) =
            CardDTO(id, null, null, null, null, listId, cix)
    }
}
