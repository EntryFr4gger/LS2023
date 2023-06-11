package pt.isel.ls.tasks.db

import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.modules.boards.BoardsDataMem
import pt.isel.ls.tasks.db.modules.cards.CardsDataMem
import pt.isel.ls.tasks.db.modules.lists.ListsDataMem
import pt.isel.ls.tasks.db.modules.tokens.TokensDataMem
import pt.isel.ls.tasks.db.modules.users.UsersDataMem
import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.db.transactionManager.TransactionManagerDM

/**
 * Data repository implementation for tasks-related data using in-memory storage.
 *
 * @param storage The TasksDataStorage instance for storing the data.
 */
class TasksDataMem(private val storage: TasksDataStorage) : TaskData {
    /**
     * Runs a function within a transaction context.
     *
     * @param function The function to be executed within the transaction context.
     *
     * @return The result of the function.
     */
    override fun <R> run(function: (TransactionManager) -> R): R = function(TransactionManagerDM())

    /**
     * Resets the data storage by clearing all stored data.
     */
    override fun reset() = storage.reset()

    /**
     * Provides access to users-related data operations.
     */
    override val users = UsersDataMem(storage)

    /**
     * Provides access to tokens-related data operations.
     */
    override val tokens = TokensDataMem(storage)

    /**
     * Provides access to boards-related data operations.
     */
    override val boards = BoardsDataMem(storage)

    /**
     * Provides access to lists-related data operations.
     */
    override val lists = ListsDataMem(storage)

    /**
     * Provides access to cards-related data operations.
     */
    override val cards = CardsDataMem(storage)
}
