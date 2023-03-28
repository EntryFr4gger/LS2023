package pt.isel.ls.tasks.db.modules.lists

import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import java.lang.Error
import java.sql.Connection
import pt.isel.ls.tasks.domain.List as _List

class ListsDataMem(private val source: TasksDataStorage): ListsDB {

    override fun createList(conn: Connection, name: String, boardId: Int): Int {
        source.nextListId.getAndIncrement().also {id->
            source.lists[id] = _List(id, name, boardId)
            return id
        }
    }

    override fun getAllLists(conn: Connection, boardId: Int): List<_List> =
        source.lists.toList().mapNotNull {
            it.second.takeIf { list->
                list.boardId == boardId }
            }



    override fun getListDetails(conn: Connection, listId: Int): _List =
        source.lists[listId] ?: throw Error("List id not found")
}