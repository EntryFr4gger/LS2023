package pt.isel.ls.tasks.services.modules.cards

import kotlinx.datetime.LocalDate
import pt.isel.ls.tasks.db.TaskData
import pt.isel.ls.tasks.domain.Card
import pt.isel.ls.tasks.services.utils.ServicesUtilsDB
import pt.isel.ls.tasks.services.utils.isValidBoardId
import pt.isel.ls.tasks.services.utils.isValidCardDescription
import pt.isel.ls.tasks.services.utils.isValidCardId
import pt.isel.ls.tasks.services.utils.isValidCardName
import pt.isel.ls.tasks.services.utils.isValidListId

/**
 * Card Services.
 * */
class CardsServices(val source: TaskData) {
    private val utils = ServicesUtilsDB(source)

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
        // DueData??
        isValidBoardId(boardId)
        listId?.let { isValidListId(it) }

        return source.run { conn ->
            // Authenticate

            utils.hasBoard(conn, boardId)
            listId?.let { utils.hasList(conn, listId) }

            source.cards.createNewCard(conn, name, description, dueDate, boardId, listId)
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

        return source.run { conn ->
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
    fun moveCard(listId: Int, cardId: Int, requestId: Int): Int {
        isValidListId(listId)
        isValidCardId(cardId)

        return source.run { conn ->
            // Authenticate

            utils.hasList(conn, listId)
            utils.hasCard(conn, cardId) // Needed?

            source.cards.moveCard(conn, listId, cardId)
        }
    }
}
