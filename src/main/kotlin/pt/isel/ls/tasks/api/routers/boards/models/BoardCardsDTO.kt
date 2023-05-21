package pt.isel.ls.tasks.api.routers.boards.models

import kotlinx.serialization.Serializable
import pt.isel.ls.tasks.api.routers.cards.models.CardDTO
import pt.isel.ls.tasks.api.routers.lists.models.ListDTO
import pt.isel.ls.tasks.domain.Card

@Serializable
data class BoardCardsDTO(val cards: List<CardDTO>) {
    companion object {
        operator fun invoke(cards: List<Card>): BoardCardsDTO =
            BoardCardsDTO(
                cards.fold(emptyList()) { acc, card ->
                    acc + CardDTO(card)
                }
            )
    }
}
