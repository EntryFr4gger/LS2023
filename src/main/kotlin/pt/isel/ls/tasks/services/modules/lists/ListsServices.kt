package pt.isel.ls.tasks.services.modules.lists

import pt.isel.ls.tasks.db.TaskData
import pt.isel.ls.tasks.domain.Card
import pt.isel.ls.tasks.services.modules.boards.response.ListDetailsResponse
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
     * Moves a card to a list.
     *
     * @param listId list unique identifier.
     * @param cardId card unique identifier.
     * @param cix desired index.
     * @param requestId request user unique identifier.
     *
     * @return a card id.
     * */
    fun respositionCard(listId: Int, cardId: Int, cix: Int, requestId: Int): Int {
        isValidListId(listId)
        isValidUserId(requestId)
        isValidCardId(cardId)
        isValidCardCix(cix)

        return source.run { conn ->
            authorizationCard(conn, cardId, requestId)
            authorizationList(conn, listId, requestId)
            organizeListCards(conn, listId, cardId, cix)
            cardId
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
    fun getListDetails(listId: Int, requestId: Int, fields: List<String>): ListDetailsResponse {
        isValidListId(listId)
        isValidFieldsListDetails(fields)
        return source.run { conn ->
            authorizationList(conn, listId, requestId)
            var cards: List<Card>? = null
            if (fields.contains("cards")) {
                cards = source.lists.getAllCards(conn, listId, 0, Int.MAX_VALUE)
            }
            ListDetailsResponse(
                source.lists.getListDetails(conn, listId),
                cards
            )
        }
    }

    /**
     * Get the set of cards in a list.
     *
     * @param listId list unique identifier.
     * @param skip skip database tables.
     * @param limit limit database tables.
     * @param requestId request user unique identifier.
     *
     * @return list of Cards in List.
     * */
    fun getCardsOfList(listId: Int, skip: Int, limit: Int, requestId: Int): List<Card> {
        isValidListId(listId)
        isValidSkipAndLimit(skip, limit)

        return source.run { conn ->
            authorizationList(conn, listId, requestId)

            source.lists.getAllCards(conn, listId, skip, limit)
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
    fun deleteList(listId: Int, requestId: Int): _List {
        isValidListId(listId)

        return source.run { conn ->
            authorizationList(conn, listId, requestId)

            source.lists.deleteList(conn, listId)
        }
    }
}
