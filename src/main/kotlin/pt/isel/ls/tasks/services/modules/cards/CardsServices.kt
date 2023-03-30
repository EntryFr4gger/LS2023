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

class CardsServices(val source: TaskData) {
    private val utils = ServicesUtilsDB(source)

    fun createNewCard(
        name: String,
        description: String,
        dueDate: LocalDate?,
        boardId: Int,
        listId: Int?
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

    fun getCardsOfList(listId: Int): List<Card> {
        isValidListId(listId)

        return source.run { conn ->
            utils.hasList(conn, listId)

            source.cards.getCardsOfList(conn, listId)
        }
    }

    fun getCardDetails(cardId: Int): Card {
        isValidCardId(cardId)

        return source.run { conn ->
            source.cards.getCardDetails(conn, cardId)
        }
    }

    fun moveCard(listId: Int, cardId: Int): Int {
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
