package pt.isel.ls.tasks.db.modules.lists

import java.sql.Connection
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
    fun createList(conn: Connection, name: String, boardId: Int): Int

    /**
     * Get the lists of a board.
     *
     * @param conn connection to database.
     * @param boardId board unique identifier.
     *
     * @return list of lists of a board.
     * */
    fun getAllLists(conn: Connection, boardId: Int): List<_List>

    /**
     * Get detailed information of a list.
     *
     * @param conn connection to database.
     * @param listId list unique identifier.
     *
     * @return a List.
     * */
    fun getListDetails(conn: Connection, listId: Int): _List

}