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
     * @return list's unique identifier.
     *
     * @throws ServicesError.InvalidArgumentException name is the worng length.
     * @throws ServicesError.InvalidArgumentException if id isn't correct.
     * @throws ServicesError.AuthorizationException if user inst authorized.
     * @throws ServicesError.InvalidArgumentException if id doesn't exists.
     * */
    fun createList(name: String, boardId: Int, requestId: Int): Int {
        isValidListName(name)
        isValidBoardId(boardId)

        return source.run { conn ->
            authorizationBoard(conn, boardId, requestId)

            hasBoard(conn, boardId)

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
     *
     * @throws ServicesError.InvalidArgumentException if id isn't correct.
     * @throws ServicesError.AuthorizationException if user inst authorized.
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
     *
     * @throws ServicesError.InvalidArgumentException if id isn't correct.
     * @throws ServicesError.AuthorizationException if user inst authorized.
     * @throws ServicesError.InvalidArgumentException if id doesn't exist.
     * */
    fun getCardsOfList(listId: Int, requestId: Int): List<Card> {
        isValidListId(listId)

        return source.run { conn ->
            authorizationList(conn, listId, requestId)

            hasList(conn, listId)

            source.lists.getCardsOfList(conn, listId)
        }
    }
}
