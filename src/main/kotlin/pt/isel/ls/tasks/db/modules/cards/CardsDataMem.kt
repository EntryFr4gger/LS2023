package pt.isel.ls.tasks.db.modules.cards

import kotlinx.datetime.LocalDate
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.errors.NotFoundException
import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.domain.Card
import java.sql.SQLException

class CardsDataMem(private val source: TasksDataStorage) : CardsDB {
    init {
        val exampleLD = LocalDate(2023, 3, 21)
        val exampleLD2 = LocalDate(2023, 4, 2)
        val exampleLD3 = LocalDate(2023, 4, 3)
        source.cards[1] = Card(1, "Phase 1", "Entrega da parte 1 do trabalho de LS", exampleLD2, 1, 1, 1)
        source.cards[2] = Card(2, "Entrega 1", "Entrega inicial do autorouter", exampleLD3, 1, 1, 2)
        source.cards[3] = Card(3, "Ração", "Ração daquela que os cães comem e tal", exampleLD, 1, 2, 3)
        source.cards[4] = Card(4, "Trela nova", "Daquela para eles n andarem muito para a frente", exampleLD, 2, 2, 3)
        source.cards[5] = Card(5, "List Null", "Archived card", exampleLD, 1, 1, null)
        source.nextCardId.addAndGet(5)
    }

    override fun createNewCard(
        conn: TransactionManager,
        name: String,
        description: String,
        cix: Int,
        dueDate: LocalDate?,
        boardId: Int,
        listId: Int?
    ) =
        source.nextCardId.getAndIncrement().let { id ->
            source.cards[id] = Card(id, name, description, dueDate, cix, boardId, listId)
            id
        }

    override fun getCardDetails(conn: TransactionManager, cardId: Int): Card =
        source.cards[cardId] ?: throw NotFoundException("Couldn't get Card($cardId) Details")

    override fun moveCard(conn: TransactionManager, listId: Int?, cardId: Int): Boolean {
        val card = source.cards[cardId] ?: throw NotFoundException("Card($cardId) Not Found")
        source.cards[cardId] = card.copy(listId = listId)
        return source.cards[cardId]?.listId == listId
    }

    override fun deleteCard(conn: TransactionManager, cardId: Int): Boolean {
        val res = source.cards.remove(cardId)
        return res != null || throw SQLException("Card($cardId) delete was unsuccessful")
    }

    override fun hasCard(conn: TransactionManager, cardId: Int): Boolean =
        source.cards[cardId] != null

    override fun organizeCardSeq(conn: TransactionManager, cardId: Int, cix: Int) {
        val card = source.cards[cardId] ?: throw NotFoundException("Card($cardId) Not Found")
        source.cards[cardId] = card.copy(cix = cix)
    }
}
