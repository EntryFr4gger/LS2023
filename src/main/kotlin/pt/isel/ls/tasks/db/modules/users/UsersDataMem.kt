package pt.isel.ls.tasks.db.modules.users

import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.domain.User
import java.sql.Connection

class UsersDataMem(private val source: TasksDataStorage): UsersDB {

    init {
        source.users[0] = User(1, "Gilberto", "Gilberto@gmail.com")
        source.users[1] = User(2, "Alberto", "Alberto@hotmail.com")
        source.users[2] = User(3, "Godofredo", "Godofredo@outlook.pt")
    }

    override fun createNewUser(conn: Connection, name: String, email: String): Int {
    override fun createNewUser(conn: TransactionManager, name: String, email: String): Int {
        source.nextUserId.getAndIncrement().also { id->
            if(source.users.values.any { it.email == email })
                throw error("Email in use")

            source.users[id] = User(id, name, email)
            return id
        }
    }

    override fun getUserDetails(conn: TransactionManager, userId: Int): User {
        return source.users[userId] ?: error("No user")
    }

}