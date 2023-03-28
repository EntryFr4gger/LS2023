package pt.isel.ls.tasks.services.lists

import pt.isel.ls.tasks.db.TaskData

class ListsServices(source: TaskData) {

    fun createList(name: String, boardId: Int): Int {
        TODO()
    }

    fun getAllLists(boardId: Int): List<pt.isel.ls.tasks.domain.List> {
        TODO()
    }

    fun getListDetails(listId: Int): pt.isel.ls.tasks.domain.List {
        TODO()
    }
}
