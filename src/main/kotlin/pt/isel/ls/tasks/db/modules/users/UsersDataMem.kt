package pt.isel.ls.tasks.db.modules.users

import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.errors.NotFoundException
import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.domain.User

class UsersDataMem(private val source: TasksDataStorage) : UsersDB {

    init {
        source.users[1] = User(1, "Gilberto", "Gilberto@gmail.com")
        source.users[2] = User(2, "Alberto", "Alberto@hotmail.com")
        source.users[3] = User(3, "Godofredo", "Godofredo@outlook.pt")
        source.users[4] = User(3, "UserWithNoBoard", "UserWithNoBoard@outlook.pt")
        source.nextUserId.addAndGet(4)
    }

    override fun createNewUser(conn: TransactionManager, name: String, email: String) =
        source.nextUserId.getAndIncrement().let { id ->
            source.users[id] = User(id, name, email)
            id
        }

    override fun getUserDetails(conn: TransactionManager, userId: Int) =
        source.users[userId] ?: throw NotFoundException("Couldn't get User($userId) Details")

    override fun getUserBoards(conn: TransactionManager, skip: Int, limit: Int, userId: Int) =
        source.userBoard[userId]?.mapNotNull { source.boards[it] } ?: emptyList()

    override fun hasUserEmail(conn: TransactionManager, email: String) =
        source.users.values.find { it.email == email } != null

    override fun hasUser(conn: TransactionManager, userId: Int) =
        source.users[userId] != null

    override fun hasUserInBoard(conn: TransactionManager, userId: Int) =
        source.userBoard[userId] != null

    override fun validateResquestBoard(conn: TransactionManager, boardId: Int, requestId: Int): Boolean {
        val userBoard = source.userBoard[requestId] ?: return false
        return userBoard.contains(boardId)
    }

    override fun validateResquestCard(conn: TransactionManager, cardId: Int, requestId: Int): Boolean {
        val userBoard = source.userBoard[requestId] ?: return false
        val card = source.cards[cardId] ?: return false
        return userBoard.contains(card.boardId)
    }

    override fun validateResquestList(conn: TransactionManager, listId: Int, requestId: Int): Boolean {
        val userBoard = source.userBoard[requestId] ?: return false
        val list = source.lists[listId] ?: return false
        return userBoard.contains(list.boardId)
    }
}
