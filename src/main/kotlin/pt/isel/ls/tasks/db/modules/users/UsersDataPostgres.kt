package pt.isel.ls.tasks.db.modules.users

import pt.isel.ls.tasks.domain.User
import java.sql.Connection
import java.sql.Statement

class UsersDataPostgres: UsersDB {

    override fun createNewUser(conn: Connection, name: String, email: String): Int {
        val obj = conn.prepareStatement(
            "INSERT INTO users(name, email) VALUES (?, ?)",
            Statement.RETURN_GENERATED_KEYS
        )
        obj.setString(1, name)
        obj.setString(2, email)

        if(obj.executeUpdate() == 0) throw Error("User Create Failed(sql)")

        obj.generatedKeys.also {
            return if (it.next()) it.getInt(1) else -1
        }
    }

    override fun getUserDetails(conn: Connection, userId: Int): User {
        val obj = conn.prepareStatement(
            "SELECT * FROM users WHERE id = ?",
        )
        obj.setInt(1, userId)

        val res = obj.executeQuery()
        if (res.next())
            return User(
                res.getInt(1),
                res.getString(2),
                res.getString(3)
            )
        else throw Error("No user")
    }
}
