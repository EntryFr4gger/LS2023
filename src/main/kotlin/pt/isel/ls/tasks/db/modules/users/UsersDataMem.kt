package pt.isel.ls.tasks.db.modules.users

import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.model.User
import java.sql.Connection

class UsersDataMem(private val source: TasksDataStorage): UsersDB {

    override fun createNewUser(conn: Connection?, name: String, email: String): Int {
        source.nextUserId.getAndIncrement().also { id->
            if(source.users.values.any { it.email == email })
                throw error("Email in use")

            source.users[id] = User(id, name, email)
            return id
        }
    }

    override fun getUserDetails(conn: Connection?, userId: Int): User {
        return source.users[userId] ?: error("No user")
    }

}