package pt.isel.ls.tasks.services.modules.lists

import pt.isel.ls.tasks.db.TaskData
import pt.isel.ls.tasks.domain.Card
import pt.isel.ls.tasks.services.utils.ServicesUtilsDB
import pt.isel.ls.tasks.services.utils.isValidBoardId
import pt.isel.ls.tasks.services.utils.isValidListId
import pt.isel.ls.tasks.services.utils.isValidListName
import pt.isel.ls.tasks.domain.List as _List

/**
 * List Services.
 * */
class ListsServices(val source: TaskData) {
    private val utils = ServicesUtilsDB(source)

    /**
     * Creates a new list in a board.
     *
     * @param name list name.
     * @param boardId board unique identifier.
     * @param requestId request user unique identifier.
     *
     * @return list's unique identifier.
     * */
    fun createList(name: String, boardId: Int, requestId: Int): Int {
        isValidListName(name)
        isValidBoardId(boardId)

        return source.run { conn ->
            // Authorized

            utils.hasBoard(conn, boardId)

            source.lists.createList(conn, name, boardId)
        }
    }

    /**
     * Get detailed information of a list.
     *
     * @param listId list unique identifier.
     * @param requestId request user unique identifier.
     *
     * @return a List.
     * */
    fun getListDetails(listId: Int, requestId: Int): _List {
        isValidListId(listId)

        return source.run { conn ->
            // Authorized

            source.lists.getListDetails(conn, listId)
        }
    }

    /**
     * Get the set of cards in a list.
     *
     * @param listId list unique identifier.
     * @param requestId request user unique identifier.
     *
     * @return list of Cards in List.
     * */
    fun getCardsOfList(listId: Int, requestId: Int): List<Card> {
        isValidListId(listId)

        return source.run { conn ->
            // Authorized

            utils.hasList(conn, listId)

            source.lists.getCardsOfList(conn, listId)
        }
    }
}
