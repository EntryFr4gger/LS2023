package pt.isel.ls.tasks.database.modules.lists

import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.modules.lists.ListsDataMem
import pt.isel.ls.tasks.model.List
import kotlin.test.assertEquals

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
        TODO("Not yet implemented")
    }

    @Test
    override fun `Throws an error for a nonexistent lists`() {
        TODO("Not yet implemented")
    }

    @Test
    override fun `Get the correct list`() {
        TODO("Not yet implemented")
    }

    @Test
    override fun `Throws an error for a nonexistent list `() {
        TODO("Not yet implemented")
    }
}