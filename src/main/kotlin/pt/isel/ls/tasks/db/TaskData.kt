package pt.isel.ls.tasks.db

import pt.isel.ls.tasks.db.modules.boards.BoardsDB
import pt.isel.ls.tasks.db.modules.cards.CardsDB
import pt.isel.ls.tasks.db.modules.lists.ListsDB
import pt.isel.ls.tasks.db.modules.tokens.TokensDB
import pt.isel.ls.tasks.db.modules.users.UsersDB
import pt.isel.ls.tasks.db.transactionManager.TransactionManager

/**
 * Interface representing the app database.
 * */
interface TaskData {

    /**
     * Executes the given function within a transaction on the database.
     *
     * @param function The function to be executed.
     *
     * @return The result of the function.
     * */
    fun <R> run(function: (TransactionManager) -> R): R

    /**
     * Resets database
     */
    fun reset()

    /**
     * Instaces of objects used for database.
     * */
    val users: UsersDB
    val tokens: TokensDB
    val boards: BoardsDB
    val lists: ListsDB
    val cards: CardsDB
}
