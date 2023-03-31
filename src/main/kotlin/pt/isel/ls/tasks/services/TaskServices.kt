package pt.isel.ls.tasks.services

import pt.isel.ls.tasks.db.TaskData
import pt.isel.ls.tasks.services.modules.boards.BoardsServices
import pt.isel.ls.tasks.services.modules.cards.CardsServices
import pt.isel.ls.tasks.services.modules.lists.ListsServices
import pt.isel.ls.tasks.services.modules.tokenHandeler.TokenServices
import pt.isel.ls.tasks.services.modules.users.UsersServices

/**
 * Task Services.
 * */
class TaskServices(source: TaskData) {
    /**
     * Instaces of objects used for services.
     * */
    val users = UsersServices(source)
    val boards = BoardsServices(source)
    val lists = ListsServices(source)
    val cards = CardsServices(source)
    val tokens = TokenServices(source)
}
