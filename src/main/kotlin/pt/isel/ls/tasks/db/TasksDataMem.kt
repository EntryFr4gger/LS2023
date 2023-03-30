package pt.isel.ls.tasks.db

import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.modules.boards.BoardsDataMem
import pt.isel.ls.tasks.db.modules.cards.CardsDataMem
import pt.isel.ls.tasks.db.modules.lists.ListsDataMem
import pt.isel.ls.tasks.db.modules.tokens.TokensDataMem
import pt.isel.ls.tasks.db.modules.users.UsersDataMem
import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.db.transactionManager.TransactionManagerDM

class TasksDataMem(storage: TasksDataStorage) : TaskData {
    override fun <R> run(function: (TransactionManager) -> R): R = function(TransactionManagerDM())

    override val users = UsersDataMem(storage)
    override val tokens = TokensDataMem(storage)
    override val boards = BoardsDataMem(storage)
    override val lists = ListsDataMem(storage)
    override val cards = CardsDataMem(storage)
}
