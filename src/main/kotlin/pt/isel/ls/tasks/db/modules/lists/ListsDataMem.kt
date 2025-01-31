package pt.isel.ls.tasks.db.modules.lists

import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.errors.NotFoundException
import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.domain.Card
import java.sql.SQLException
import pt.isel.ls.tasks.domain.List as _List

class ListsDataMem(private val source: TasksDataStorage) : ListsDB {
    init {
        source.lists[1] = _List(1, "Aula de LS", 1)
        source.lists[2] = _List(2, "Aula de LAE", 1)
        source.lists[3] = _List(3, "Compras Cadela", 2)
        source.nextListId.addAndGet(3)
    }

    override fun createList(conn: TransactionManager, name: String, boardId: Int) =
        source.nextListId.getAndIncrement().let { id ->
            source.lists[id] = _List(id, name, boardId)
            id
        }

    override fun getListDetails(conn: TransactionManager, listId: Int): _List =
        source.lists[listId] ?: throw NotFoundException("Couldn't get List($listId) Details")

    override fun getAllCards(conn: TransactionManager, listId: Int, skip: Int, limit: Int): List<Card> {
        val filteredCards = source.cards.toList().mapNotNull {
            it.second.takeIf { card ->
                card.listId == listId
            }
        }
        val startIndex = minOf(skip, filteredCards.size)
        val endIndex = minOf(startIndex + limit, filteredCards.size)
        return filteredCards.subList(startIndex, endIndex)
    }

    override fun deleteList(conn: TransactionManager, listId: Int): Boolean {
        val res = source.lists.remove(listId)
        return res != null || throw SQLException("List($listId) delete was unsuccessful")
    }

    override fun hasList(conn: TransactionManager, listId: Int): Boolean =
        source.lists[listId] != null
}
