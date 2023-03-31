package pt.isel.ls.tasks.services.modules.lists

import pt.isel.ls.tasks.db.TaskData
import pt.isel.ls.tasks.domain.Card
import pt.isel.ls.tasks.services.utils.ServicesUtilsDB
import pt.isel.ls.tasks.services.utils.isValidBoardId
import pt.isel.ls.tasks.services.utils.isValidListId
import pt.isel.ls.tasks.services.utils.isValidListName
import pt.isel.ls.tasks.domain.List as _List

class ListsServices(val source: TaskData) {
    private val utils = ServicesUtilsDB(source)

    fun createList(name: String, boardId: Int, requestId: Int): Int {
        isValidListName(name)
        isValidBoardId(boardId)

        return source.run { conn ->
            // Authenticate

            utils.hasBoard(conn, boardId)

            source.lists.createList(conn, name, boardId)
        }
    }

    fun getListDetails(listId: Int, requestId: Int): _List {
        isValidListId(listId)

        return source.run { conn ->
            source.lists.getListDetails(conn, listId)
        }
    }

    fun getCardsOfList(listId: Int, requestId: Int): List<Card> {
        isValidListId(listId)

        return source.run { conn ->
            utils.hasList(conn, listId)

            source.lists.getCardsOfList(conn, listId)
        }
    }
}
