package pt.isel.ls.tasks.services

import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.modules.boards.BoardsDataMem
import pt.isel.ls.tasks.db.modules.cards.CardsDataMem
import pt.isel.ls.tasks.db.modules.lists.ListsDataMem
import pt.isel.ls.tasks.db.modules.users.UsersDataMem
import pt.isel.ls.tasks.db.transactionManager.TransactionManagerDM
import pt.isel.ls.tasks.domain.Board
import pt.isel.ls.tasks.domain.User
import pt.isel.ls.tasks.domain.List as _List
import kotlin.collections.List

class Services {
    val storage = TasksDataStorage()
    private val userData = UsersDataMem(storage)
    private val boardData = BoardsDataMem(storage)
    private val listsData = ListsDataMem(storage)
    private val cardsData = CardsDataMem(storage)
    val connection = TransactionManagerDM()
    fun getUser(id : Int): User {
        return userData.getUserDetails(connection,id)
    }

    //adds user to database
    fun addUser(name: String, email: String): Int {
        return userData.createNewUser(connection,name,email)
    }

    fun getBoards(id: Int): List<Board> {
        return boardData.getUserBoards(connection,id)
    }

    fun getBoard(boardId: Int): Board {
        return boardData.getBoardDetails(connection,boardId)
    }

    fun addUserToBoard(user_id: Int, board_id: Int): Any {
        TODO("Not yet implemented")
    }

    fun createBoard(name: String, description: String): Int {
        TODO("Not yet implemented")
    }

    fun getList(listId: Int): _List {
        TODO("Not yet implemented")
    }

    fun getLists(boardId: Int): List<_List> {
        TODO("Not yet implemented")
    }

    fun createList(name: String, board_id: Int): Int {
        TODO("Not yet implemented")
    }
}
