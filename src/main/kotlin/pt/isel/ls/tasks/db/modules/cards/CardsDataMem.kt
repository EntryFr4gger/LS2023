package pt.isel.ls.tasks.db.modules.cards

import kotlinx.datetime.LocalDate
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.domain.Card
import java.sql.Connection

class CardsDataMem(private val source: TasksDataStorage): CardsDB {

     override fun createNewCard(
        conn: TransactionManager,
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

    override fun getCardsOfList(conn: TransactionManager, listId: Int): List<Card>  =
        source.cards.toList().mapNotNull {
            it.second.takeIf { card ->
                card.listId == listId
            }
        }


    override fun getCardDetails(conn: TransactionManager, cardId: Int, listId: Int): Card {
        val cards = getCardsOfList(conn,listId)
        return cards.first{ it.id == cardId}
    }
//Refazer
    override fun moveCard(conn: TransactionManager, cardId: Int, lid: Int):Int {
        val card = source.cards[cardId] ?: error("card not find")
        source.cards[cardId] = card.copy(listId = lid)
        return 1
    }
}