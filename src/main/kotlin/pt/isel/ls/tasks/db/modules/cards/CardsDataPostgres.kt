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
        val res = conn.prepareStatement(
            "INSERT INTO cards(name, description, duedate, board_id) VALUES (?, ?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS
        )
        res.setString(1, name)
        res.setString(2, description)
        res.setString(3, "23:44:59.903")
        res.setInt(4, boardId)

        if(res.executeUpdate() == 0) throw Error("Card Create Failed(sql)")

        res.generatedKeys.also {
            return if (it.next()) it.getInt(1) else -1
        }
    }

    override fun getCardsOfList(conn: Connection, listId: Int): List<Card> {
        val prp = conn.prepareStatement(
            "SELECT * FROM cards WHERE list_id = ?",
        )
        prp.setInt(1, listId)

        val res = prp.executeQuery()

        var list  = emptyList<Card>()
        while (res.next()){
            list += Card(
                res.getInt(1),
                res.getString(2),
                res.getString(3),
                res.getDate(4).toLocalDate().toKotlinLocalDate(),
                res.getInt(5),
                res.getInt(6)
            )
        }
        if (list.isNotEmpty())
            return list
        else
            throw Error("No Cards")
    }

    override fun getCardDetails(conn: Connection, cardId: Int, listId: Int): Card {
        val prp = conn.prepareStatement(
            "SELECT * FROM cards WHERE id = ?",
        )
        prp.setInt(1, cardId)

        val res = prp.executeQuery()
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

    override fun moveCard(conn: Connection, cardId: Int, lid: Int): Card {
        TODO("Not yet implemented")
    }


}