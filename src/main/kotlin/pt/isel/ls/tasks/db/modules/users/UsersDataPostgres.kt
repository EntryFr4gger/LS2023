package pt.isel.ls.tasks.db.modules.users

import pt.isel.ls.tasks.domain.User
import java.sql.Connection
import java.sql.Statement

class UsersDataPostgres(): UsersDB {

    override fun createNewUser(conn: Connection, name: String, email: String): Int {
        val res = conn.prepareStatement(
            "INSERT INTO users(name, email) VALUES (?, ?)",
            Statement.RETURN_GENERATED_KEYS
        )
        res.setString(1, name)
        res.setString(2, email)

        if(res.executeUpdate() == 0) throw Error("User Create Failed(sql)")

        res.generatedKeys.also {
            return if (it.next()) it.getInt(1) else -1
        }
    }
//transaction manage
    override fun getUserDetails(conn: Connection, userId: Int): User {
        val prp = conn.prepareStatement(
            "SELECT * FROM users WHERE id = ?",
        )
        prp.setInt(1, userId)

        val res = prp.executeQuery()
        if (res.next())
            return User(
                res.getInt(1),
                res.getString(2),
                res.getString(3)
            )
        else throw Error("No user")
    }
}
