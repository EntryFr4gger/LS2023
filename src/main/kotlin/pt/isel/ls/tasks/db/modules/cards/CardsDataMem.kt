package pt.isel.ls.tasks.db.modules.cards

import kotlinx.datetime.LocalDate
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.errors.NotFoundException
import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.domain.Card

class CardsDataMem(private val source: TasksDataStorage) : CardsDB {
    init {
        val exampleLD = LocalDate(2023, 3, 21)
        val exampleLD2 = LocalDate(2023, 4, 2)
        val exampleLD3 = LocalDate(2023, 4, 3)
        source.cards[1] = Card(1, "Phase 1", "Entrega da parte 1 do trabalho de LS", exampleLD2, 1, 1)
        source.cards[2] = Card(2, "Entrega 1", "Entrega inicial do autorouter", exampleLD3, 1, 2)
        source.cards[3] = Card(3, "Ração", "Ração daquela que os cães comem e tal", exampleLD, 2, 3)
        source.cards[4] = Card(4, "Trela nova", "Daquela para eles n andarem muito para a frente", exampleLD, 2, 3)
        source.nextCardId.addAndGet(4)
    }

    // make due date optional
    override fun createNewCard(
        conn: TransactionManager,
        name: String,
        description: String,
        dueDate: LocalDate?,
        boardId: Int,
        listId: Int?
    ): Int {
        source.nextCardId.getAndIncrement().also { id ->
            source.cards[id] = Card(id, name, description, dueDate, boardId, listId)
            return id
        }
    }

    override fun getCardDetails(conn: TransactionManager, cardId: Int): Card =
        source.cards[cardId] ?: throw NotFoundException()

    override fun moveCard(conn: TransactionManager, listId: Int, cardId: Int): Boolean {
        val card = source.cards[cardId] ?: error("card not find")
        source.cards[cardId] = card.copy(listId = listId)
        return source.cards[cardId]?.listId != null
    }

    override fun hasCard(conn: TransactionManager, cardId: Int): Boolean =
        source.cards[cardId] != null
}
