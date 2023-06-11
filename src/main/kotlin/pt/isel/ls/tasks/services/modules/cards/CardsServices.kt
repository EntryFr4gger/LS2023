package pt.isel.ls.tasks.services.modules.cards

import kotlinx.datetime.LocalDate
import pt.isel.ls.tasks.db.TaskData
import pt.isel.ls.tasks.domain.Card
import pt.isel.ls.tasks.services.utils.ServicesUtils

/**
 * Card Services.
 * */
class CardsServices(source: TaskData) : ServicesUtils(source) {

    /**
     * Creates a new card in a list.
     *
     * @param name the task name.
     * @param description the task description.
     * @param dueDate the task's conclusion date.
     * @param boardId board unique identifier.
     * @param listId list unique identifier.
     * @param requestId request user unique identifier.
     *
     * @return new card unique identifier.
     * */
    fun createNewCard(
        name: String,
        description: String,
        dueDate: LocalDate?,
        boardId: Int,
        listId: Int,
        requestId: Int
    ): Int {
        isValidCardName(name)
        isValidCardDescription(description)
        isValidBoardId(boardId)
        isValidListId(listId)
        isValidUserId(requestId)

        return source.run { conn ->
            authorizationBoard(conn, boardId, requestId)
            authorizationList(conn, listId, requestId)

            val cix = source.lists.getAllCards(conn, listId, 0, Int.MAX_VALUE).maxByOrNull { it.cix }?.cix ?: 0
            source.cards.createNewCard(conn, name, description, cix + 1, dueDate, boardId, listId)
        }
    }

    /**
     * Get the detailed information of a card.
     *
     * @param cardId card unique identifier.
     * @param requestId request user unique identifier.
     *
     * @return a Card.
     * */
    fun getCardDetails(cardId: Int, requestId: Int): Card {
        isValidCardId(cardId)
        isValidUserId(requestId)

        return source.run { conn ->
            authorizationCard(conn, cardId, requestId)

            source.cards.getCardDetails(conn, cardId)
        }
    }

    /**
     * Moves a card to a list.
     *
     * @param listId list unique identifier.
     * @param cardId card unique identifier.
     * @param requestId request user unique identifier.
     *
     * @return a card id.
     * */
    fun moveCard(cardId: Int, listId: Int?, requestId: Int): Boolean {
        listId?.let { isValidListId(it) }
        isValidCardId(cardId)
        isValidUserId(requestId)

        return source.run { conn ->
            authorizationCard(conn, cardId, requestId)
            listId?.let { authorizationList(conn, listId, requestId) }
            val card = source.cards.getCardDetails(conn, cardId)
            source.cards.moveCard(conn, listId, cardId).also { card.listId?.let { organizeCards(conn, it) } }
        }
    }

    /**
     * Delete a card.
     *
     * @param cardId card unique identifier.
     * @param requestId request user unique identifier.
     *
     * @return true if it has deleted or false otherwise.
     * */
    fun deleteCard(cardId: Int, requestId: Int): Boolean {
        isValidCardId(cardId)
        isValidUserId(requestId)

        return source.run { conn ->
            authorizationCard(conn, cardId, requestId)
            var sucess: Boolean
            source.cards.getCardDetails(conn, cardId)
                .also { card ->
                    sucess = source.cards.deleteCard(conn, cardId)
                    card.listId?.let { id -> organizeCards(conn, id) }
                }
            sucess
        }
    }
}
