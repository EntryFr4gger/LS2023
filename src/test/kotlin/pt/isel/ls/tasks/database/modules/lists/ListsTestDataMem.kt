package pt.isel.ls.tasks.database.modules.lists

import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.modules.lists.ListsDataMem
import pt.isel.ls.tasks.domain.List
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ListsTestDataMem : ListsTestDB {
    private val storage = TasksDataStorage()
    private val source = TasksDataMem(storage)
    private val list = ListsDataMem(storage)

    @Test
    override fun `List is created correctly and with right identifier`() {
        source.run { conn ->
            val list = List(1, "Study", 1)
            val id = this.list.createList(conn, list.name, list.boardId)

            assertEquals(id, list.id)
            assertEquals(list, storage.lists[id])
        }
    }

    @Test
    override fun `Gets the correct lists of a board`() {
        source.run { conn ->
            val lists = listOf<List>(List(1, "Study", 1), List(2, "Work", 1))
            val ids = lists.map { list.createList(conn, it.name, it.boardId) }
            assertEquals(ids, listOf(1, 2))
            assertEquals(lists, list.getAllLists(conn, 1))
        }
    }

    // @Test
    override fun `Throws an error for a nonexistent lists`() {
        source.run { conn ->
            assertFailsWith<IllegalStateException> {
                list.getListDetails(conn, 1)
            }
        }
    }

    @Test
    override fun `Get the correct list`() {
        source.run { conn ->
            val lists = listOf(List(1, "Study", 1), List(2, "Work", 1))
            val ids = lists.map { list.createList(conn, it.name, it.boardId) }
            assertEquals(lists[1], list.getListDetails(conn, 2))
        }
    }

    override fun `Throws an error for a nonexistent list `() {
        TODO("Not yet implemented")
    }
}
