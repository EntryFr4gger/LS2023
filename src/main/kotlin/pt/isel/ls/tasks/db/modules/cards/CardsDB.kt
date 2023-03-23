package pt.isel.ls.tasks.db.modules.cards

import kotlinx.datetime.LocalDate
import pt.isel.ls.tasks.domain.Card
import java.sql.Connection

/**
 * Card Management
 * */
interface CardsDB {

    /**
     * Creates a new card in a list.
     *
     * @param conn connection to database.
     * @param name the task name.
     * @param description the task description.
     * @param dueDate the task's conclusion date.
     * @param boardId board unique identifier.
     *
     * @return card unique identifier.
     * */
    fun createNewCard(
        conn: Connection,
        name: String,
        description: String,
        dueDate: LocalDate,
        boardId: Int,
        listId: Int?
    ): Int

    /**
     * Get the set of cards in a list.
     *
     * @param conn connection to database.
     * @param listId list unique identifier.
     *
     * @return list of Cards in List.
     * */
    fun getCardsOfList(conn: Connection, listId: Int): List<Card>

    /**
     * Get the detailed information of a card.
     *
     * @param conn connection to database.
     * @param cardId card unique identifier.
     * @param listId list unique identifier.
     *
     * @return a Card.
     * */
    fun getCardDetails(conn: Connection, cardId: Int, listId: Int): Card

    /**
     * Moves a card to a list.
     *
     * @param conn connection to database.
     * @param cardId card unique identifier.
     * @param lid list unique identifier.
     *
     * @return a Card.
     * */
    fun moveCard(conn: Connection, cardId: Int, lid: Int): Int
}