package pt.isel.ls.tasks.db.modules.users

import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.domain.Board
import pt.isel.ls.tasks.domain.User

class UsersDataMem(private val source: TasksDataStorage) : UsersDB {

    init {
        source.users[1] = User(1, "Gilberto", "Gilberto@gmail.com")
        source.users[2] = User(2, "Alberto", "Alberto@hotmail.com")
        source.users[3] = User(3, "Godofredo", "Godofredo@outlook.pt")
        source.users[4] = User(3, "UserWithNoBoard", "UserWithNoBoard@outlook.pt")
        source.nextUserId.addAndGet(4)
    }

    override fun createNewUser(conn: TransactionManager, name: String, email: String): Int {
        source.nextUserId.getAndIncrement().also { id ->
            if (source.users.values.any { it.email == email }) {
                throw error("Email in use")
            }

            source.users[id] = User(id, name, email)
            return id
        }
    }

    override fun getUserDetails(conn: TransactionManager, userId: Int): User {
        return source.users[userId] ?: error("No user")
    }

    override fun getUserBoards(conn: TransactionManager, userId: Int): List<Board> {
        val userBoard = source.userBoard[userId]
        return userBoard?.mapNotNull { source.boards[it] } ?: error("User with no boards")
    }

    override fun hasUserEmail(conn: TransactionManager, email: String): Boolean =
        source.users.values.find { it.email == email } != null

    override fun hasUser(conn: TransactionManager, userId: Int): Boolean =
        source.users[userId] != null

    override fun hasUserInBoard(conn: TransactionManager, userId: Int): Boolean =
        source.userBoard[userId] != null

    override fun validateResquestBoard(conn: TransactionManager, boardId: Int, requestId: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun validateResquestCard(conn: TransactionManager, cardId: Int, requestId: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun validateResquestList(conn: TransactionManager, listId: Int, requestId: Int): Boolean {
        TODO("Not yet implemented")
    }
}
