package pt.isel.ls.tasks.db.modules.lists

import pt.isel.ls.tasks.db.errors.NotFoundException
import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.domain.Card
import pt.isel.ls.tasks.domain.List as _List

/**
 * List Management
 * */
interface ListsDB {

    /**
     * Creates a new list in a board.
     *
     * @param conn connection to a database.
     * @param name list name.
     * @param boardId board unique identifier.
     *
     * @return list's unique identifier.
     * */
    fun createList(conn: TransactionManager, name: String, boardId: Int): Int

    /**
     * Get detailed information of a list.
     *
     * @param conn connection to a database.
     * @param listId list unique identifier.
     *
     * @return a List.
     *
     * @throws NotFoundException couldn't get List Details
     * */
    fun getListDetails(conn: TransactionManager, listId: Int): _List

    /**
     * Get the set of cards in a list.
     *
     * @param conn connection to a database.
     * @param listId list unique identifier.
     * @param skip skip tables.
     * @param limit limits the return values.
     *
     * @return list of Cards in List.
     * */
    fun getCardsOfList(conn: TransactionManager, listId: Int, skip: Int, limit: Int): List<Card>

    /**
     * Delete a list.
     *
     * @param conn connection to a database.
     * @param listId list unique identifier.
     *
     * @return true if it has deleted or false otherwise.
     * */
    fun deleteList(conn: TransactionManager, listId: Int)

    /**
     * Verify if list exists.
     *
     * @param conn connection to a database.
     * @param listId list unique identifier.
     *
     * @return true if exists or false if it doesn't exist.
     */
    fun hasList(conn: TransactionManager, listId: Int): Boolean
}
