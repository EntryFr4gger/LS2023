package pt.isel.ls.tasks.db.modules.cards

import kotlinx.datetime.LocalDate
import pt.isel.ls.tasks.domain.Card
import java.sql.Connection

class CardsDataPostgres: CardsDB {
    override fun createNewCard(
        conn: Connection?,
        name: String,
        description: String,
        dueDate: LocalDate,
        boardId: Int,
        listId: Int?
    ): Int {
        TODO("Not yet implemented")
    }

    override fun getAllCards(conn: Connection?, listId: Int): List<Card> {
        TODO("Not yet implemented")
    }

    override fun getCardDetails(conn: Connection?, cardId: Int, listId: Int): Card {
        TODO("Not yet implemented")
    }

    override fun moveCard(conn: Connection?, cardId: Int, lid: Int): Card {
        TODO("Not yet implemented")
    }


}