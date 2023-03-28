package pt.isel.ls.tasks.services.cards

import pt.isel.ls.tasks.db.TaskData
import pt.isel.ls.tasks.domain.Card

class CardsServices(source: TaskData) {

    fun getCardsOfList(listId: Int): List<Card> {
        TODO()
    }

    fun getCardDetails(cardId: Int): Card {
        TODO()
    }

    fun moveCard(listId: Int, cardId: Int): Int {
        TODO()
    }
}
