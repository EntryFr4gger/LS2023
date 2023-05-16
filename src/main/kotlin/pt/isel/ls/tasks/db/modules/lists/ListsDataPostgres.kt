package pt.isel.ls.tasks.db.modules.lists

import pt.isel.ls.tasks.db.errors.NotFoundException
import pt.isel.ls.tasks.db.modules.cards.CardsDataPostgres.Companion.toCard
import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.db.transactionManager.connection
import pt.isel.ls.tasks.domain.Card
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement
import kotlin.collections.List
import pt.isel.ls.tasks.domain.List as _List

class ListsDataPostgres : ListsDB {

    companion object {
        fun ResultSet.toList() =
            _List(getInt(1), getString(2), getInt(3))
    }

    override fun createList(conn: TransactionManager, name: String, boardId: Int): Int {
        val obj = conn.connection().prepareStatement(
            "INSERT INTO lists(name, board_id) VALUES (?, ?)",
            Statement.RETURN_GENERATED_KEYS
        )
        obj.setString(1, name)
        obj.setInt(2, boardId)

        if (obj.executeUpdate() == 0) {
            throw SQLException("List Creation Failed with name:$name in board:$boardId")
        }

        obj.generatedKeys.also {
            return if (it.next()) it.getInt(1) else -1
        }
    }

    override fun getListDetails(conn: TransactionManager, listId: Int): _List {
        val obj = conn.connection().prepareStatement(
            "SELECT * FROM lists WHERE id = ?"
        )
        obj.setInt(1, listId)

        val res = obj.executeQuery()
        if (res.next()) {
            return res.toList()
        } else {
            throw NotFoundException("Couldn't get List($listId) Details")
        }
    }

    override fun getCardsOfList(conn: TransactionManager, listId: Int, skip: Int, limit: Int): List<Card> {
        val obj = conn.connection().prepareStatement(
            "SELECT * FROM cards WHERE list_id = ? ORDER BY cix DESC OFFSET ? LIMIT ?"
        )
        obj.setInt(1, listId)
        obj.setInt(2, skip)
        obj.setInt(3, limit)

        val res = obj.executeQuery()

        val cards = mutableListOf<Card>()
        while (res.next())
            cards.add(res.toCard())

        return cards
    }

    override fun deleteList(conn: TransactionManager, listId: Int) {
        val res = conn.connection().prepareStatement(
            "DELETE FROM lists WHERE id = ?"
        )
        res.setInt(1, listId)
        if (res.executeUpdate() == 0) throw SQLException("List($listId) delete was unsuccessful")
    }

    override fun hasList(conn: TransactionManager, listId: Int): Boolean {
        val res = conn.connection().prepareStatement(
            "SELECT * FROM lists WHERE id = ?"
        )
        res.setInt(1, listId)

        return res.executeQuery().next()
    }
}
