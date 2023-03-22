package pt.isel.ls.tasks.db.modules.cards

import kotlinx.datetime.LocalDate
import pt.isel.ls.tasks.domain.Card
import java.sql.Connection

/**
 *
 * */
interface CardsDB {

    /**
     *
     *  @param name the task name.
     *  @param description the task description.
     *  @param dueDate the task's conclusion date.
     *
     *  @return card unique identifier.
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
     *
     * */
    fun getCardsOfList(conn: Connection, listId: Int): List<Card>

    /**
     *
     * */
    fun getCardDetails(conn: Connection, cardId: Int, listId: Int): Card

    /**
     *
     * */
    fun moveCard(conn: Connection, cardId: Int, lid: Int): Card
}