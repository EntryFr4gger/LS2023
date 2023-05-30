package pt.isel.ls.tasks.db.modules.cards

import kotlinx.datetime.LocalDate
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toKotlinLocalDate
import pt.isel.ls.tasks.db.errors.NotFoundException
import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.db.transactionManager.connection
import pt.isel.ls.tasks.domain.Card
import java.sql.Date
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
                getIntOrNull(5),
                getInt(6),
                getIntOrNull(7)
            )

        private fun ResultSet.getIntOrNull(columnIndex: Int): Int? {
            return try {
                getInt(columnIndex)
            } catch (e: SQLException) {
                null
            }
        }

        fun PreparedStatement.setStringIfNotNull(parameterIndex: Int, data: String?, type: Int) =
            if (data == null) setNull(parameterIndex, type) else setString(parameterIndex, data)

        fun PreparedStatement.setIntIfNotNull(parameterIndex: Int, data: Int?, type: Int) =
            if (data == null) setNull(parameterIndex, type) else setInt(parameterIndex, data)

        fun PreparedStatement.setDateIfNotNull(parameterIndex: Int, data: LocalDate?, type: Int) =
            if (data == null) {
                setNull(parameterIndex, type)
            } else {
                setDate(
                    parameterIndex,
                    Date.valueOf(data.toJavaLocalDate())
                )
            }
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
        obj.setDateIfNotNull(3, dueDate, Types.DATE)
        obj.setInt(4, boardId)
        obj.setIntIfNotNull(5, listId, Types.INTEGER)

        if (obj.executeUpdate() == 0) throw SQLException("Card Creation Failed with name:$name")

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
            throw NotFoundException("Couldn't get Card($cardId) Details")
        }
    }

    override fun moveCard(conn: TransactionManager, listId: Int?, cardId: Int) {
        val obj = conn.connection().prepareStatement(
            "UPDATE cards SET list_id = ? WHERE id = ?",
            Statement.RETURN_GENERATED_KEYS
        )

        obj.setIntIfNotNull(1, listId, Types.INTEGER)
        obj.setInt(2, cardId)

        if (obj.executeUpdate() == 0) {
            throw SQLException("Card Move Failed with cardId: $cardId and listId: $listId")
        }

        obj.generatedKeys.also {
            it.next()
        }
    }

    override fun deleteCard(conn: TransactionManager, cardId: Int) {
        val res = conn.connection().prepareStatement(
            "DELETE FROM cards WHERE id = ?"
        )
        res.setInt(1, cardId)
        if (res.executeUpdate() == 0) throw SQLException("Card($cardId) delete was unsuccessful")
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

        obj.setInt(1, cix)
        obj.setInt(2, cardId)

        if (obj.executeUpdate() == 0) throw SQLException("Organization Card($cardId) Failed to index $cix")

        obj.generatedKeys.also {
            return it.next()
        }
    }
}
