package pt.isel.ls.tasks.services

import pt.isel.ls.tasks.db.TaskData
import pt.isel.ls.tasks.services.modules.boards.BoardsServices
import pt.isel.ls.tasks.services.modules.cards.CardsServices
import pt.isel.ls.tasks.services.modules.lists.ListsServices
import pt.isel.ls.tasks.services.modules.users.UsersServices

class TaskServices(source: TaskData) {
    val users: UsersServices = UsersServices(source)
    val boards: BoardsServices = BoardsServices(source)
    val lists: ListsServices = ListsServices(source)
    val cards: CardsServices = CardsServices(source)
}
