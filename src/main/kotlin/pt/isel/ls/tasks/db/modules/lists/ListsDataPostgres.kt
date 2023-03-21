package pt.isel.ls.tasks.db.modules.lists

import java.sql.Connection

class ListsDataPostgres(): ListsDB {
    override fun createList(conn: Connection?, name: String, boardId: Int): Int {
        TODO("Not yet implemented")
    }

    override fun getAllLists(conn: Connection?, boardId: Int): List<pt.isel.ls.tasks.domain.List> {
        TODO("Not yet implemented")
    }

    override fun getListDetails(conn: Connection?, listId: Int): pt.isel.ls.tasks.domain.List {
        TODO("Not yet implemented")
    }

}