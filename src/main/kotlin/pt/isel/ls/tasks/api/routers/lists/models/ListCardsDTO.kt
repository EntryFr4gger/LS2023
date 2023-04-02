package pt.isel.ls.tasks.api.routers.lists.models

import kotlinx.serialization.Serializable
import pt.isel.ls.tasks.api.routers.cards.models.CardDTO
import pt.isel.ls.tasks.domain.Card

@Serializable
data class ListCardsDTO(val cards: List<CardDTO>) {
    companion object {
        operator fun invoke(cards: List<Card>): ListCardsDTO =
            ListCardsDTO(
                cards.fold(emptyList()) { acc, card ->
                    acc + CardDTO(card)
                }
            )
    }
}
