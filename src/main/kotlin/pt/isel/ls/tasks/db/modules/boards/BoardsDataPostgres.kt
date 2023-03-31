package pt.isel.ls.tasks.db.modules.boards

import pt.isel.ls.tasks.db.errors.DBError
import pt.isel.ls.tasks.db.modules.lists.ListsDataPostgres.Companion.toList
import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.db.transactionManager.connection
import pt.isel.ls.tasks.domain.Board
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

class BoardsDataPostgres : BoardsDB {

    companion object {
        fun ResultSet.toBoard() =
            Board(getInt(1), getString(2), getString(3))
    }

    override fun createNewBoard(conn: TransactionManager, name: String, description: String): Int {
        val res = conn.connection().prepareStatement(
            "INSERT INTO boards(name, description) VALUES (?, ?)",
            Statement.RETURN_GENERATED_KEYS
        )
        res.setString(1, name)
        res.setString(2, description)

        if (res.executeUpdate() == 0) throw SQLException("Board Creation Failed")

        res.generatedKeys.also {
            return if (it.next()) it.getInt(1) else -1
        }
    }

    override fun addUserToBoard(conn: TransactionManager, userId: Int, boardId: Int): Int {
        val res = conn.connection().prepareStatement(
            "INSERT INTO user_board(user_id, board_id) VALUES (?, ?)",
            Statement.RETURN_GENERATED_KEYS
        )
        res.setInt(1, userId)
        res.setInt(2, boardId)

        if (res.executeUpdate() == 0) throw SQLException("User Addition to Board Failed")

        res.generatedKeys.also {
            return if (it.next()) it.getInt(1) else -1
        }
    }

    override fun getBoardDetails(conn: TransactionManager, boardId: Int): Board {
        val prp = conn.connection().prepareStatement(
            "SELECT * FROM boards WHERE id = ?"
        )
        prp.setInt(1, boardId)

        val res = prp.executeQuery()
        if (res.next()) {
            return res.toBoard()
        } else {
            throw DBError.NotFoundException()
        }
    }

    override fun getAllLists(conn: TransactionManager, boardId: Int): List<pt.isel.ls.tasks.domain.List> {
        val obj = conn.connection().prepareStatement(
            "SELECT * FROM lists WHERE board_id = ?"
        )
        obj.setInt(1, boardId)

        val res = obj.executeQuery()

        val lists = mutableListOf<pt.isel.ls.tasks.domain.List>()
        while (res.next())
            lists += res.toList()

        return lists
    }

    override fun isNewName(conn: TransactionManager, name: String): Boolean {
        val res = conn.connection().prepareStatement(
            "SELECT * FROM boards WHERE name = ?"
        )
        res.setString(1, name)

        return res.executeQuery().next()
    }

    override fun hasBoard(conn: TransactionManager, boardId: Int): Boolean {
        val res = conn.connection().prepareStatement(
            "SELECT * FROM boards WHERE id = ?"
        )
        res.setInt(1, boardId)

        return res.executeQuery().next()
    }


}
