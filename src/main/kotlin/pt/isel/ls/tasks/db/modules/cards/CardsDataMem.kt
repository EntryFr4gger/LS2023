package pt.isel.ls.tasks.db.modules.cards

import kotlinx.datetime.LocalDate
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.domain.Card
import java.sql.Connection

class CardsDataMem(private val source: TasksDataStorage): CardsDB {

    override fun createNewCard(
        conn: Connection?,
        name: String,
        description: String,
        dueDate: LocalDate,
        boardId: Int,
        listId: Int?
    ): Int {
        source.nextCardId.getAndIncrement().also {id->
            source.cards[id] = Card(id, name, description, dueDate, boardId, listId)
            return id
        }
    }

    override fun getAllCards(conn: Connection?, listId: Int): List<Card>  =
        source.cards.toList().mapNotNull {
            it.second.takeIf { card ->
                card.listId == listId
            }
        }


    override fun getCardDetails(conn: Connection?, cardId: Int, listId: Int): Card {
        val cards = getAllCards(null,listId)
        return cards.first{ it.id == cardId}
    }

    override fun moveCard(conn: Connection?, cardId: Int, lid: Int):Card {
        val card = source.cards[cardId] ?: error("card not find")
        val newCard =  Card(cardId,card.name,card.description,card.dueDate,card.boardId,lid)
        source.cards[cardId] = newCard
        return newCard
    }
}