package pt.isel.ls.tasks.db.modules.lists

import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.domain.List as _List

/**
 * List Management
 * */
interface ListsDB {

    /**
     * Creates a new list on a board.
     *
     * @param conn connection to database.
     * @param name list name.
     * @param boardId board unique identifier.
     *
     * @return list's unique identifier.
     * */
    fun createList(conn: TransactionManager, name: String, boardId: Int): Int

    /**
     * Get the lists of a board.
     *
     * @param conn connection to database.
     * @param boardId board unique identifier.
     *
     * @return list of lists of a board.
     * */
    fun getAllLists(conn: TransactionManager, boardId: Int): List<_List>

    /**
     * Get detailed information of a list.
     *
     * @param conn connection to database.
     * @param listId list unique identifier.
     *
     * @return a List.
     * */
    fun getListDetails(conn: TransactionManager, listId: Int): _List
}
