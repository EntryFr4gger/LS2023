package pt.isel.ls.tasks.db.modules.users

import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.errors.NotFoundException
import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.domain.User

class UsersDataMem(private val source: TasksDataStorage) : UsersDB {

    init {
        source.users[1] = User(1, "Admin", "Admin@gmail.com", "6593D31A65175D624AFC703A4070DB550D4C7B91C795E431DA9A69E52C1F313E") // Admin123&
        source.users[2] = User(2, "Rafa", "rafaelDCosta@outlook.com", "D5989C7FFC36711AF4BD46606D051ECD70A45C581E85428C8B129722C260EBEE") // Drago123&
        source.users[3] = User(3, "Bernardo", "BSerra@outlook.pt", "880E1FBAA9260190E5CF57C34A4523EE7FD7056486922EE273053F2ED38C9A52") // Serr123&
        source.users[4] = User(3, "UserWithNoBoard", "UserWithNoBoard@outlook.pt", "D12CC6817061AA42A23AE259DED1F419C45D03DB2C2EE02ACC4784A9761D781A") // Adsfs123&
        source.nextUserId.addAndGet(4)
    }

    override fun createNewUser(conn: TransactionManager, name: String, email: String, password: String) =
        source.nextUserId.getAndIncrement().let { id ->
            source.users[id] = User(id, name, email, password)
            id
        }

    override fun loginUserInfo(conn: TransactionManager, email: String): User {
        return source.users.values.find { it.email == email }
            ?: throw NotFoundException("Couldn't get User with email($email) Details")
    }

    override fun getUserDetails(conn: TransactionManager, userId: Int) =
        source.users[userId] ?: throw NotFoundException("Couldn't get User($userId) Details")

    override fun getUserBoards(conn: TransactionManager, skip: Int, limit: Int, userId: Int) =
        source.userBoard[userId]?.mapNotNull { source.boards[it] } ?: emptyList()

    override fun deleteBoardUsers(conn: TransactionManager, boardId: Int) {
        source.boards.remove(boardId)
    }

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
