package pt.isel.ls.tasks.db.modules.users

import pt.isel.ls.tasks.model.User
import java.sql.Connection

class UsersDataPostgres(): UsersDB {

    override fun createNewUser(conn: Connection?, name: String, email: String): Int {
        TODO("Not yet implemented")
    }

    override fun getUserDetails(conn: Connection?, userId: Int): User {
        TODO("Not yet implemented")
    }
}