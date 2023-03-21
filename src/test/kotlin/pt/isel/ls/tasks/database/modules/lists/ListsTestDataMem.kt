package pt.isel.ls.tasks.database.modules.lists

import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.modules.lists.ListsDataMem
import pt.isel.ls.tasks.model.Board
import pt.isel.ls.tasks.model.List
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ListsTestDataMem: ListsTestDB {
    private val storage = TasksDataStorage()
    private val source = ListsDataMem(storage)

    @Test
    override fun `List is created correctly and with right identifier`() {
        val list = List(1, "Study", 1)
        val id = source.createList(null, list.name, list.boardId)

        assertEquals(id, list.id)
        assertEquals(list, storage.lists[id])
    }

    @Test
    override fun `Gets the correct lists of a board`() {
        val lists = listOf<List>(List(1, "Study", 1),  List(2, "Work", 1))
        val ids = lists.map { source.createList(null, it.name,it.boardId) }
        assertEquals(ids, listOf(1,2) )
        assertEquals(lists,source.getAllLists(null,1))
    }

    @Test
    override fun `Throws an error for a nonexistent lists`() {
        assertFailsWith<IllegalStateException> {
                source.getListDetails(null, 1)
        }
    }

    @Test
    override fun `Get the correct list`() {
        val lists = listOf<List>(List(1, "Study", 1),  List(2, "Work", 1))
        val ids = lists.map { source.createList(null, it.name,it.boardId) }
        assertEquals(lists[1],source.getListDetails(null,2))
    }

    override fun `Throws an error for a nonexistent list `() {
        TODO("Not yet implemented")
    }
}