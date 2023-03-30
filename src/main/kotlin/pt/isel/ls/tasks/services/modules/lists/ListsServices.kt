package pt.isel.ls.tasks.services.modules.lists

import pt.isel.ls.tasks.db.TaskData
import pt.isel.ls.tasks.services.utils.ServicesUtilsDB
import pt.isel.ls.tasks.services.utils.isValidBoardId
import pt.isel.ls.tasks.services.utils.isValidListId
import pt.isel.ls.tasks.services.utils.isValidListName
import kotlin.collections.List
import pt.isel.ls.tasks.domain.List as _List

class ListsServices(val source: TaskData) {
    private val utils = ServicesUtilsDB(source)

    fun createList(name: String, boardId: Int): Int {
        isValidListName(name)
        isValidBoardId(boardId)

        return source.run { conn ->
            // Authenticate

            utils.hasBoard(conn, boardId)

            source.lists.createList(conn, name, boardId)
        }
    }
    fun getAllLists(boardId: Int): List<_List> {
        isValidBoardId(boardId)

        return source.run { conn ->
            source.lists.getAllLists(conn, boardId)
        }
    }

    fun getListDetails(listId: Int): _List {
        isValidListId(listId)

        return source.run { conn ->
            source.lists.getListDetails(conn, listId)
        }
    }
}
