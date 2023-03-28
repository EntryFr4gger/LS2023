package pt.isel.ls.tasks.db.modules.cards

import kotlinx.datetime.LocalDate
import kotlinx.datetime.toKotlinLocalDate
import pt.isel.ls.tasks.domain.Card
import java.sql.Connection
import java.sql.Statement

class CardsDataPostgres: CardsDB {
    override fun createNewCard(
        conn: Connection,
        name: String,
        description: String,
        dueDate: LocalDate,
        boardId: Int,
        listId: Int?
    ): Int {
        val obj = conn.prepareStatement(
            "INSERT INTO cards(name, description, duedate, board_id) VALUES (?, ?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS
        )
        obj.setString(1, name)
        obj.setString(2, description)
        obj.setString(3, "23:44:59.903")
        obj.setInt(4, boardId)

        if(obj.executeUpdate() == 0) throw Error("Card Create Failed(sql)")

        obj.generatedKeys.also {
            return if (it.next()) it.getInt(1) else -1
        }
    }

    override fun getCardsOfList(conn: Connection, listId: Int): List<Card> {
        val obj = conn.prepareStatement(
            "SELECT * FROM cards WHERE list_id = ?",
        )
        obj.setInt(1, listId)

        val res = obj.executeQuery()

        val cards = mutableListOf<Card>()
        while (res.next()){
            cards += Card(
                res.getInt(1),
                res.getString(2),
                res.getString(3),
                res.getDate(4).toLocalDate().toKotlinLocalDate(),
                res.getInt(5),
                res.getInt(6)
            )
        }
        return cards
    }

    override fun getCardDetails(conn: Connection, cardId: Int, listId: Int): Card {
        val obj = conn.prepareStatement(
            "SELECT * FROM cards WHERE id = ?",
        )
        obj.setInt(1, cardId)

        val res = obj.executeQuery()
        if (res.next())
            return Card(
                res.getInt(1),
                res.getString(2),
                res.getString(3),
                res.getDate(4).toLocalDate().toKotlinLocalDate(),
                res.getInt(5),
                res.getInt(6)
            )
        else throw Error("No Card")
    }

    override fun moveCard(conn: Connection, cardId: Int, lid: Int): Int {
        val obj = conn.prepareStatement(
            "UPDATE cards SET list_id = ? WHERE id = ?",
            Statement.RETURN_GENERATED_KEYS
        )
        obj.setInt(1, lid)
        obj.setInt(2, cardId)

        if(obj.executeUpdate() == 0) throw Error("Card Create Failed(sql)")

        obj.generatedKeys.also {
            return if (it.next()) it.getInt(1) else -1
        }
    }


}