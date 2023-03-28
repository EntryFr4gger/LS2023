package pt.isel.ls.tasks.db.modules.cards

import kotlinx.datetime.LocalDate
import kotlinx.datetime.toKotlinLocalDate
import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.db.transactionManager.connection
import pt.isel.ls.tasks.domain.Card
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement
import java.sql.Types

class CardsDataPostgres: CardsDB {

    companion object{
        fun ResultSet.toCard() =
            Card(getInt(1), getString(2), getString(3),
                getDate(4)?.toLocalDate()?.toKotlinLocalDate(),
                getInt(5), getInt(6)
        )

        fun <T> PreparedStatement.setType(parameterIndex : Int, data: T?, type: Int) =
            if (data == null) setNull(parameterIndex, type) else setString(parameterIndex, data.toString())
    }

    override fun createNewCard(
        conn: TransactionManager,
        name: String,
        description: String,
        dueDate: LocalDate?,
        boardId: Int,
        listId: Int?
    ): Int {
        val obj = conn.connection().prepareStatement(
            "INSERT INTO cards(name, description, duedate, board_id, list_id) VALUES (?, ?, ?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS
        )
        obj.setString(1, name)
        obj.setString(2, description)
        obj.setType(3,dueDate, Types.CHAR)
        obj.setInt(4, boardId)
        obj.setType(5,listId, Types.INTEGER)

        if(obj.executeUpdate() == 0) throw Error("Card Create Failed(sql)")

        obj.generatedKeys.also {
            return if (it.next()) it.getInt(1) else -1
        }
    }

    override fun getCardsOfList(conn: TransactionManager, listId: Int): List<Card> {
        val obj = conn.connection().prepareStatement(
            "SELECT * FROM cards WHERE list_id = ?",
        )
        obj.setInt(1, listId)

        val res = obj.executeQuery()

        val cards = mutableListOf<Card>()
        while (res.next())
            cards.add(res.toCard())

        return cards
    }

    override fun getCardDetails(conn: TransactionManager, cardId: Int): Card {
        val obj = conn.connection().prepareStatement(
            "SELECT * FROM cards WHERE id = ?",
        )
        obj.setInt(1, cardId)

        val res = obj.executeQuery()
        if (res.next())
            return res.toCard()
        else
            throw Error("No Card")
    }

    override fun moveCard(conn: TransactionManager, listId: Int, cardId: Int): Int {
        val obj = conn.connection().prepareStatement(
            "UPDATE cards SET list_id = ? WHERE id = ?",
            Statement.RETURN_GENERATED_KEYS
        )
        obj.setInt(1, listId)
        obj.setInt(2, cardId)

        if(obj.executeUpdate() == 0) throw Error("Card Create Failed(sql)")

        obj.generatedKeys.also {
            return if (it.next()) it.getInt(1) else -1
        }
    }


}