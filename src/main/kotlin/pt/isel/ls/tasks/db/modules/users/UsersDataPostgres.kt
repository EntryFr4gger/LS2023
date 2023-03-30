package pt.isel.ls.tasks.db.modules.users

import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.db.transactionManager.connection
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
            Statement.RETURN_GENERATED_KEYS,
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
            "SELECT * FROM users WHERE id = ?",
        )
        obj.setInt(1, userId)

        val res = obj.executeQuery()
        if (res.next()) {
            return res.toUser()
        } else {
            throw Error("No user")
        }
    }

    override fun isNewEmail(conn: TransactionManager, email: String): Boolean {
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
}
