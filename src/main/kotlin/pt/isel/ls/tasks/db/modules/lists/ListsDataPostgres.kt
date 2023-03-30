package pt.isel.ls.tasks.db.modules.lists

import pt.isel.ls.tasks.db.errors.DBError
import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.db.transactionManager.connection
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement
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

        if (obj.executeUpdate() == 0) throw SQLException("List Creation Failed")

        obj.generatedKeys.also {
            return if (it.next()) it.getInt(1) else -1
        }
    }

    override fun getAllLists(conn: TransactionManager, boardId: Int): List<_List> {
        val obj = conn.connection().prepareStatement(
            "SELECT * FROM lists WHERE board_id = ?"
        )
        obj.setInt(1, boardId)

        val res = obj.executeQuery()

        val lists = mutableListOf<_List>()
        while (res.next())
            lists += res.toList()

        return lists
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
            throw DBError.NotFoundException()
        }
    }

    override fun hasList(conn: TransactionManager, listId: Int): Boolean {
        val res = conn.connection().prepareStatement(
            "SELECT * FROM lists WHERE id = ?"
        )
        res.setInt(1, listId)

        return res.executeQuery().next()
    }
}
