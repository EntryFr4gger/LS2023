package pt.isel.ls.tasks.db.modules.cards

import kotlinx.datetime.LocalDate
import kotlinx.datetime.toKotlinLocalDate
import pt.isel.ls.tasks.db.TasksDataPostgres
import pt.isel.ls.tasks.db.errors.NotFoundException
import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.db.transactionManager.connection
import pt.isel.ls.tasks.domain.Card
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement
import java.sql.Types

class CardsDataPostgres : CardsDB {

    companion object {
        fun ResultSet.toCard() =
            Card(
                getInt(1),
                getString(2),
                getString(3),
                getDate(4)?.toLocalDate()?.toKotlinLocalDate(),
                getInt(5),
                getInt(6),
                getInt(7)
            )

        fun PreparedStatement.setStringIfNotNull(parameterIndex: Int, data: String?, type: Int) =
            if (data == null) setNull(parameterIndex, type) else setString(parameterIndex, data)

        fun PreparedStatement.setIntIfNotNull(parameterIndex: Int, data: Int?, type: Int) =
            if (data == null) setNull(parameterIndex, type) else setInt(parameterIndex, data)
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
        obj.setStringIfNotNull(3, dueDate.toString(), Types.CHAR)
        obj.setInt(4, boardId)
        obj.setIntIfNotNull(5, listId, Types.INTEGER)

        if (obj.executeUpdate() == 0) throw SQLException("Card Creation Failed")

        obj.generatedKeys.also {
            return if (it.next()) it.getInt(1) else -1
        }
    }

    override fun getCardDetails(conn: TransactionManager, cardId: Int): Card {
        val obj = conn.connection().prepareStatement(
            "SELECT * FROM cards WHERE id = ?"
        )
        obj.setInt(1, cardId)

        val res = obj.executeQuery()
        if (res.next()) {
            return res.toCard()
        } else {
            throw NotFoundException()
        }
    }

    override fun moveCard(conn: TransactionManager, listId: Int, cardId: Int): Boolean {
        val obj = conn.connection().prepareStatement(
            "UPDATE cards SET list_id = ? WHERE id = ?",
            Statement.RETURN_GENERATED_KEYS
        )
        obj.setInt(1, listId)
        obj.setInt(2, cardId)

        if (obj.executeUpdate() == 0) throw SQLException("Card Move Failed")

        obj.generatedKeys.also {
            return it.next()
        }
    }

    override fun deleteCard(conn: TransactionManager, cardId: Int) {
        val res = conn.connection().prepareStatement(
            "DELETE FROM cards WHERE id = ?"
        )
        res.setInt(1, cardId)
        if (res.executeUpdate() == 0) throw SQLException("Card Delete was unsuccessful")
    }

    override fun hasCard(conn: TransactionManager, cardId: Int): Boolean {
        val res = conn.connection().prepareStatement(
            "SELECT * FROM cards WHERE id = ?"
        )
        res.setInt(1, cardId)

        return res.executeQuery().next()
    }

    override fun organizeCardSeq(conn: TransactionManager, cardId: Int, cix: Int): Boolean {
        val obj = conn.connection().prepareStatement(
            "UPDATE cards SET cix = ? WHERE id = ?",
            Statement.RETURN_GENERATED_KEYS
        )

        obj.setInt(1,cix)
        obj.setInt(2, cardId)

        if (obj.executeUpdate() == 0) throw SQLException("Organization Card Failed")

        obj.generatedKeys.also {
            return it.next()
        }
    }
}

fun main(){
    val source = TasksDataPostgres("JDBC_DATABASE_URL")
    val cards = CardsDataPostgres()

    source.run {conn->
        repeat(5){
            cards.moveCard(conn, 1,it+ 1)
            cards.organizeCardSeq(conn, it+1, it+1)
        }

    }
}
