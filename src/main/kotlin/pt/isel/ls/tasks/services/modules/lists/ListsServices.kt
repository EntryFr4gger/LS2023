package pt.isel.ls.tasks.services.modules.lists

import pt.isel.ls.tasks.db.TaskData
import pt.isel.ls.tasks.domain.Card
import pt.isel.ls.tasks.services.utils.ServicesUtils
import kotlin.collections.List
import pt.isel.ls.tasks.domain.List as _List

/**
 * List Services.
 * */
class ListsServices(source: TaskData) : ServicesUtils(source) {

    /**
     * Creates a new list in a board.
     *
     * @param name list name.
     * @param boardId board unique identifier.
     * @param requestId request user unique identifier.
     *
     * @return new list unique identifier.
     * */
    fun createList(name: String, boardId: Int, requestId: Int): Int {
        isValidListName(name)
        isValidBoardId(boardId)

        return source.run { conn ->
            authorizationBoard(conn, boardId, requestId)

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
            authorizationList(conn, listId, requestId)

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
    fun getCardsOfList(listId: Int, skip: Int, limit: Int, requestId: Int): List<Card> {
        isValidListId(listId)

        return source.run { conn ->
            authorizationList(conn, listId, requestId)

            source.lists.getCardsOfList(conn, listId, skip, limit)
        }
    }

    /**
     * Delete a list.
     *
     * @param listId list unique identifier.
     * @param requestId request user unique identifier.
     *
     * @return true if it has deleted or false otherwise.
     * */
    fun deleteList(listId: Int, requestId: Int) {
        isValidListId(listId)

        return source.run { conn ->
            authorizationList(conn, listId, requestId)

            source.lists.deleteList(conn, listId)
        }
    }
}
