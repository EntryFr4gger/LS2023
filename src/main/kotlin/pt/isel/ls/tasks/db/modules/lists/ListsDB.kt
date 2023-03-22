package pt.isel.ls.tasks.db.modules.lists

import java.sql.Connection
import pt.isel.ls.tasks.domain.List as _List

/**
 *
 * */
interface ListsDB {

    /**
     *
     *  @param name list name.
     *
     *  @return list unique identifier.
     * */
    fun createList(conn: Connection, name: String, boardId: Int): Int

    /**
     *
     * */
    fun getAllLists(conn: Connection, boardId: Int): List<_List>

    /**
     *
     * */
    fun getListDetails(conn: Connection, listId: Int): _List

}