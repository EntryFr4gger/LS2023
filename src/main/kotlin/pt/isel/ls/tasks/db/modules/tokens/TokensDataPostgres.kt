package pt.isel.ls.tasks.db.modules.tokens

import pt.isel.ls.tasks.db.errors.DBError
import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.db.transactionManager.connection
import java.sql.SQLException
import java.sql.Statement

class TokensDataPostgres : TokensDB {
    override fun createNewToken(conn: TransactionManager, token: String, userId: Int): String {
        val obj = conn.connection().prepareStatement(
            "INSERT INTO tokens(token, user_id) VALUES (?, ?)",
            Statement.RETURN_GENERATED_KEYS
        )
        obj.setString(1, token)
        obj.setInt(2, userId)

        if (obj.executeUpdate() == 0) throw SQLException("Token Creation Failed")

        return token
    }

    override fun getUserID(conn: TransactionManager, token: String): Int {
        val obj = conn.connection().prepareStatement(
            "SELECT user_id FROM tokens WHERE token = ?"
        )
        obj.setString(1, token)

        val res = obj.executeQuery()
        if (res.next()) {
            return res.getInt(1)
        } else {
            throw DBError.NotFoundException()
        }
    }

    override fun hasToken(conn: TransactionManager, token: String): Boolean {
        val res = conn.connection().prepareStatement(
            "SELECT user_id FROM tokens WHERE token = ?"
        )
        res.setString(1, token)

        return res.executeQuery().next()
    }
}
