package pt.isel.ls.tasks.services

import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.modules.boards.BoardsDataMem
import pt.isel.ls.tasks.db.modules.cards.CardsDataMem
import pt.isel.ls.tasks.db.modules.lists.ListsDataMem
import pt.isel.ls.tasks.db.modules.users.UsersDataMem
import pt.isel.ls.tasks.domain.User
import java.sql.Connection

class Services {
    val storage = TasksDataStorage()
    private val userData = UsersDataMem(storage)
    private val boardData = BoardsDataMem(storage)
    private val listsData = ListsDataMem(storage)
    private val cardsData = CardsDataMem(storage)
    val connection = null as Connection
    fun getUser(id : Int): User {
        return userData.getUserDetails(connection,id)
    }

    //adds user to database
    fun addUser(name: String, email: String): Int {
        return userData.createNewUser(connection,name,email)
    }

    fun getBoards(id: Int): Any {
        return boardData.getUserBoards(connection,id)
    }
}
