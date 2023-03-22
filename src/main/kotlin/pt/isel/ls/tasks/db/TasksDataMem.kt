package pt.isel.ls.tasks.db

import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.modules.boards.BoardsDataMem
import pt.isel.ls.tasks.db.modules.cards.CardsDataMem
import pt.isel.ls.tasks.db.modules.lists.ListsDataMem
import pt.isel.ls.tasks.db.modules.users.UsersDataMem
import java.sql.Connection


class TasksDataMem(storage: TasksDataStorage): TaskData{
    override fun <R> execute(func: (Connection) -> R): R = func(null as Connection)


    override val users = UsersDataMem(storage)
    override val boards = BoardsDataMem(storage)
    override val lists = ListsDataMem(storage)
    override val cards = CardsDataMem(storage)
}