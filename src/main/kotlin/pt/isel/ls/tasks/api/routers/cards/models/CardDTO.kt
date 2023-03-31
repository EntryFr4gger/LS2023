package pt.isel.ls.tasks.api.routers.cards.models

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import pt.isel.ls.tasks.domain.Card

@Serializable
data class CardDTO(val id: Int, val name: String, val description: String, val dueDate: LocalDate? = null) {
    companion object {
        operator fun invoke(card: Card) = CardDTO(card.id, card.name, card.description, card.dueDate)
    }
}
