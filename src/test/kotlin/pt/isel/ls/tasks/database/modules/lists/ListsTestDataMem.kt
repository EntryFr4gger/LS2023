package pt.isel.ls.tasks.database.modules.lists

import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.modules.lists.ListsDataMem
import pt.isel.ls.tasks.domain.List
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ListsTestDataMem  {
    private val storage = TasksDataStorage()
    private val source = TasksDataMem(storage)
    private val lists = ListsDataMem(storage)

    @Test
     fun `List is created correctly and with right identifier`() {
        source.execute { conn ->
            val lists = List(1, "Study", 1)
            val id = this.lists.createList(conn, lists.name, lists.boardId)
            val listCreated = lists.copy(id=id)
            assertEquals(listCreated, storage.lists[id])
        }
    }

    @Test
     fun `Gets the correct lists of a board`() {
        source.execute { conn ->
            val lists = listOf(List(1, "Aula de LS", 1),
            List(2, "Aula de LAE", 1))
            val res =
            assertEquals(lists, this.lists.getAllLists(conn, 1))
        }
    }

    @Test
     fun `Throws an error for a nonexistent lists`() {
        source.execute { conn ->
            assertFailsWith<IllegalStateException> {
                lists.getListDetails(conn, 10)
            }
        }
    }

    @Test
     fun `Get the correct list`() {
        source.execute { conn ->
            val list = List(1, "Aula de LS", 1)
            assertEquals(list, lists.getListDetails(conn, 1))
        }
    }

     fun `Throws an error for a nonexistent list `() {
        TODO("Not yet implemented")
    }
}
