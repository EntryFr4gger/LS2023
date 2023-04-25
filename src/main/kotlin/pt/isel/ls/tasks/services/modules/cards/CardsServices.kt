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
     * @return card unique identifier.
     *
     * @throws ServicesError.InvalidArgumentException name is the worng length.
     * @throws ServicesError.InvalidArgumentException description is the worng length.
     * @throws ServicesError.InvalidArgumentException if board id isn't correct.
     * @throws ServicesError.InvalidArgumentException if list id isn't correct.
     * @throws ServicesError.AuthorizationException if user inst authorized.
     * @throws ServicesError.InvalidArgumentException if id doesn't exist.
     * */
    fun createNewCard(
        name: String,
        description: String,
        dueDate: LocalDate?,
        boardId: Int,
        listId: Int?,
        requestId: Int
    ): Int {
        isValidCardName(name)
        isValidCardDescription(description)
        isValidBoardId(boardId)
        listId?.let { isValidListId(it) }

        return source.run { conn ->
            authorizationBoard(conn, boardId, requestId)

            hasBoard(conn, boardId)
            listId?.let { hasList(conn, listId) }

            source.cards.createNewCard(conn, name, description, dueDate, boardId, listId)
                .also {
                    listId?.let { organizeCards(conn, listId) }
                }
        }
    }

    /**
     * Get the detailed information of a card.
     *
     * @param cardId card unique identifier.
     * @param requestId request user unique identifier.
     *
     * @return a Card.
     *
     * @throws ServicesError.InvalidArgumentException if card id isn't correct.
     * @throws ServicesError.AuthorizationException if user inst authorized.
     * */
    fun getCardDetails(cardId: Int, requestId: Int): Card {
        isValidCardId(cardId)

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
     *
     * @throws ServicesError.InvalidArgumentException if id isn't correct.
     * @throws ServicesError.InvalidArgumentException if id isn't correct.
     * @throws ServicesError.InvalidArgumentException if id doesn't exists.
     * @throws ServicesError.InvalidArgumentException if id doesn't exists.
     * */
    fun moveCard(listId: Int, cardId: Int, cix: Int?, requestId: Int): Boolean {
        isValidListId(listId)
        isValidCardId(cardId)
        cix?.let { isValidCardCix(cix) }

        return source.run { conn ->
            authorizationCard(conn, cardId, requestId)
            authorizationList(conn, listId, requestId)

            hasList(conn, listId)
            hasCard(conn, cardId)

            val bol = source.cards.moveCard(conn, listId, cardId)
            cix?.let { organizeAfterMove(conn, listId, cardId, cix) }
            bol
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
    fun deleteCard(cardId: Int, requestId: Int) {
        isValidCardId(cardId)

        return source.run { conn ->
            authorizationCard(conn, cardId, requestId)

            hasCard(conn, cardId)

            val listId = source.cards.getCardDetails(conn, cardId).listId!!
            source.cards.deleteCard(conn, cardId)
            organizeCards(conn, listId)
        }
    }
}
