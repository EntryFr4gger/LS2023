package pt.isel.ls.tasks.db.modules.boards

import pt.isel.ls.tasks.db.errors.NotFoundException
import pt.isel.ls.tasks.db.modules.lists.ListsDataPostgres.Companion.toList
import pt.isel.ls.tasks.db.modules.users.UsersDataPostgres.Companion.toUser
import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.db.transactionManager.connection
import pt.isel.ls.tasks.domain.Board
import pt.isel.ls.tasks.domain.User
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement
import kotlin.collections.List
import kotlin.collections.mutableListOf
import kotlin.collections.plusAssign
import pt.isel.ls.tasks.domain.List as _List

class BoardsDataPostgres : BoardsDB {

    companion object {
        fun ResultSet.toBoard() =
            Board(getInt(1), getString(2), getString(3))
    }

    override fun createNewBoard(conn: TransactionManager, name: String, description: String): Int {
        val prp = conn.connection().prepareStatement(
            "INSERT INTO boards(name, description) VALUES (?, ?)",
            Statement.RETURN_GENERATED_KEYS
        )
        prp.setString(1, name)
        prp.setString(2, description)

        if (prp.executeUpdate() == 0) throw SQLException("New Board Creation Failed with name: $name")

        prp.generatedKeys.also {
            return if (it.next()) it.getInt(1) else -1
        }
    }

    override fun addUserToBoard(conn: TransactionManager, userId: Int, boardId: Int): Boolean {
        val prp = conn.connection().prepareStatement(
            "INSERT INTO user_board(user_id, board_id) VALUES (?, ?)",
            Statement.RETURN_GENERATED_KEYS
        )
        prp.setInt(1, userId)
        prp.setInt(2, boardId)

        if (prp.executeUpdate() == 0) {
            throw SQLException("User Addition to Board Failed with userId: $userId and boardId: $boardId")
        }

        prp.generatedKeys.also {
            return it.next()
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
            throw NotFoundException("Couldn't get Board($boardId) Details")
        }
    }

    override fun getAllLists(conn: TransactionManager, boardId: Int, skip: Int, limit: Int): List<_List> {
        val prp = conn.connection().prepareStatement(
            "SELECT * FROM lists WHERE board_id = ? OFFSET ? LIMIT ?"
        )
        prp.setInt(1, boardId)
        prp.setInt(2, skip)
        prp.setInt(3, limit)

        val res = prp.executeQuery()

        val lists = mutableListOf<_List>()
        while (res.next())
            lists += res.toList()

        return lists
    }

    override fun getBoardUsers(conn: TransactionManager, boardId: Int, skip: Int, limit: Int): List<User> {
        val prp = conn.connection().prepareStatement(
            """
                SELECT id, name, email FROM users u
                    JOIN user_board ub ON ub.user_id = u.id
                    WHERE board_id = ?
                    OFFSET ? 
                    LIMIT ?
            """.trimIndent()
        )

        prp.setInt(1, boardId)
        prp.setInt(2, skip)
        prp.setInt(3, limit)

        val res = prp.executeQuery()

        val users = mutableListOf<User>()
        while (res.next())
            users += res.toUser()

        return users
    }

    override fun searchBoards(conn: TransactionManager, skip: Int, limit: Int, name: String, userId: Int): List<Board> {
        val prp = conn.connection().prepareStatement(
            """
                SELECT id, name, description FROM boards b
                    JOIN user_board ub ON ub.board_id = b.id
                    WHERE name ILIKE ? AND user_id = ?
                    OFFSET ? 
                    LIMIT ?
            """.trimIndent()
        )

        prp.setString(1, "%$name%")
        prp.setInt(2, userId)
        prp.setInt(3, skip)
        prp.setInt(4, limit)

        val res = prp.executeQuery()

        val boards = mutableListOf<Board>()
        while (res.next())
            boards += res.toBoard()

        return boards
    }

    override fun deleteBoard(conn: TransactionManager, boardId: Int): Board {
        val obj = conn.connection().prepareStatement(
            "DELETE FROM boards WHERE id = ? RETURNING *"
        )
        obj.setInt(1, boardId)
        val res = obj.executeQuery()
        return if(res.next()) res.toBoard() else throw SQLException("Board($boardId) delete was unsuccessful")
    }

    override fun hasBoardName(conn: TransactionManager, name: String): Boolean {
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
