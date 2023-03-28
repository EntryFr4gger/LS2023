package pt.isel.ls.tasks.services

import pt.isel.ls.tasks.db.TaskData
import pt.isel.ls.tasks.services.boards.BoardsServices
import pt.isel.ls.tasks.services.cards.CardsServices
import pt.isel.ls.tasks.services.lists.ListsServices
import pt.isel.ls.tasks.services.users.UsersServices

class TaskServices(source: TaskData) {
    val users: UsersServices = UsersServices(source)
    val boards: BoardsServices = BoardsServices(source)
    val lists: ListsServices = ListsServices(source)
    val cards: CardsServices = CardsServices(source)
}
