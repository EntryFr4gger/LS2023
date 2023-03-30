package pt.isel.ls.tasks.services.users

import pt.isel.ls.tasks.db.TaskData
import pt.isel.ls.tasks.domain.User

class UsersServices(val source: TaskData) {

    // Quando mudarm
    fun createNewUser(name: String, email: String): Int {
        // Nome valido
        // Email valido

        return source.execute { conn ->
            // Verifica se email Existe
            val userID = source.users.createNewUser(conn, name, email)
            val token = TODO()

            userID
        }
    }

    fun getUserDetails(userId: Int): User {
        TODO()
    }
}
