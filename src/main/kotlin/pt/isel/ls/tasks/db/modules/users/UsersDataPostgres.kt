package pt.isel.ls.tasks.db.modules.users

import pt.isel.ls.tasks.db.errors.NotFoundException
import pt.isel.ls.tasks.db.modules.boards.BoardsDataPostgres.Companion.toBoard
import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.db.transactionManager.connection
import pt.isel.ls.tasks.domain.Board
import pt.isel.ls.tasks.domain.User
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

class UsersDataPostgres : UsersDB {

    companion object {
        fun ResultSet.toUser() =
            User(getInt(1), getString(2), getString(3))
    }

    override fun createNewUser(conn: TransactionManager, name: String, email: String): Int {
        val obj = conn.connection().prepareStatement(
            "INSERT INTO users(name, email) VALUES (?, ?)",
            Statement.RETURN_GENERATED_KEYS
        )
        obj.setString(1, name)
        obj.setString(2, email)

        if (obj.executeUpdate() == 0) throw SQLException("User Creation Failed")

        obj.generatedKeys.also {
            return if (it.next()) it.getInt(1) else -1
        }
    }

    override fun getUserDetails(conn: TransactionManager, userId: Int): User {
        val obj = conn.connection().prepareStatement(
            "SELECT * FROM users WHERE id = ?"
        )
        obj.setInt(1, userId)

        val res = obj.executeQuery()
        if (res.next()) {
            return res.toUser()
        } else {
            throw NotFoundException()
        }
    }

    override fun getUserBoards(conn: TransactionManager, skip: Int, limit: Int, userId: Int): List<Board> {
        val obj = conn.connection().prepareStatement(
            "SELECT * FROM boards JOIN user_board ON id = board_id WHERE user_id = ? OFFSET ? LIMIT ?"
        )
        obj.setInt(1, userId)
        obj.setInt(2, skip)
        obj.setInt(3, limit)

        val res = obj.executeQuery()

        val boards = mutableListOf<Board>()
        while (res.next())
            boards.add(res.toBoard())

        return boards
    }

    override fun hasUserEmail(conn: TransactionManager, email: String): Boolean {
        val res = conn.connection().prepareStatement(
            "SELECT * FROM users WHERE email = ?"
        )
        res.setString(1, email)

        return res.executeQuery().next()
    }

    override fun hasUser(conn: TransactionManager, userId: Int): Boolean {
        val res = conn.connection().prepareStatement(
            "SELECT * FROM users WHERE id = ?"
        )
        res.setInt(1, userId)

        return res.executeQuery().next()
    }

    override fun hasUserInBoard(conn: TransactionManager, userId: Int): Boolean {
        val res = conn.connection().prepareStatement(
            "SELECT * FROM user_board WHERE user_id = ?"
        )
        res.setInt(1, userId)

        return res.executeQuery().next()
    }

    override fun validateResquestBoard(conn: TransactionManager, boardId: Int, requestId: Int): Boolean {
        val obj = conn.connection().prepareStatement(
            "SELECT * FROM user_board WHERE board_id = ?"
        )
        obj.setInt(1, boardId)

        val res = obj.executeQuery()
        while (res.next())
            if (res.getInt(1) == requestId) {
                return true
            }
        return false
    }

    override fun validateResquestCard(conn: TransactionManager, cardId: Int, requestId: Int): Boolean {
        val obj = conn.connection().prepareStatement(
            """
                SELECT user_id FROM user_board ub
                    JOIN cards c ON c.board_id = ub.board_id
                    WHERE id = ?
            """.trimIndent()
        )
        obj.setInt(1, cardId)

        val res = obj.executeQuery()
        while (res.next())
            if (res.getInt(1) == requestId) {
                return true
            }
        return false
    }

    override fun validateResquestList(conn: TransactionManager, listId: Int, requestId: Int): Boolean {
        val obj = conn.connection().prepareStatement(
            """
                SELECT user_id FROM user_board ub
                    JOIN lists l ON l.board_id = ub.board_id
                    WHERE id = ?
            """.trimIndent()
        )
        obj.setInt(1, listId)

        val res = obj.executeQuery()
        while (res.next())
            if (res.getInt(1) == requestId) {
                return true
            }
        return false
    }
}
