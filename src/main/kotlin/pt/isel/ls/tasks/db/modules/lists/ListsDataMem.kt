package pt.isel.ls.tasks.db.modules.lists

import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import java.lang.Error
import pt.isel.ls.tasks.domain.List as _List

class ListsDataMem(private val source: TasksDataStorage) : ListsDB {
    init {
        source.lists[1] = _List(1, "Aula de LS", 1)
        source.lists[2] = _List(2, "Aula de LAE", 1)
        source.lists[3] = _List(3, "Compras Cadela", 2)
        source.nextListId.addAndGet(3)
    }

    override fun createList(conn: TransactionManager, name: String, boardId: Int): Int {
        source.nextListId.getAndIncrement().also { id ->
            source.lists[id] = _List(id, name, boardId)
            return id
        }
    }

    override fun getAllLists(conn: TransactionManager, boardId: Int): List<_List> =
        source.lists.toList().mapNotNull {
            it.second.takeIf { list ->
                list.boardId == boardId
            }
        }

    override fun getListDetails(conn: TransactionManager, listId: Int): _List =
        source.lists[listId] ?: throw Error("List id not found")

    override fun hasList(conn: TransactionManager, listId: Int): Boolean {
        TODO("Not yet implemented")
    }
}
