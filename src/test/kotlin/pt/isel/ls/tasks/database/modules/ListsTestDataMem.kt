package pt.isel.ls.tasks.database.modules

import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.modules.lists.ListsDataMem
import pt.isel.ls.tasks.domain.Card
import pt.isel.ls.tasks.domain.List
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ListsTestDataMem {
    private val storage = TasksDataStorage()
    private val source = TasksDataMem(storage)
    private val lists = ListsDataMem(storage)

    @Test
    fun `List is created correctly and with right identifier`() {
        source.run { conn ->
            val lists = List(1, "Study", 1)
            val id = this.lists.createList(conn, lists.name, lists.boardId)
            val listCreated = lists.copy(id = id)
            assertEquals(listCreated, storage.lists[id])
        }
    }

    @Test
    fun `get all cards in the same list`() {
        source.run { conn ->
            val cards = listOf(
                Card(3, "Ração", "Ração daquela que os cães comem e tal", LocalDate(2023, 3, 21), 2, 3),
                Card(4, "Trela nova", "Daquela para eles n andarem muito para a frente", LocalDate(2023, 3, 21), 2, 3)
            )

            val res = this.lists.getCardsOfList(conn, 3)
            assertEquals(cards, res)
        }
    }

    @Test
    fun `Get the correct list`() {
        source.run { conn ->
            val list = List(1, "Aula de LS", 1)
            val res = lists.getListDetails(conn, 1)
            assertEquals(list, res)
        }
    }

    @Test
    fun `Throws an error for a nonexistent lists`() {
        source.run { conn ->
            assertFailsWith<IllegalStateException> {
                lists.getListDetails(conn, 10)
            }
        }
    }

    @Test
    fun `Confirm that the list already exist`() {
        source.run { conn ->
            assertTrue { lists.hasList(conn, 1) }
        }
    }

    @Test
    fun `Confirm that the list do not exist`() {
        source.run { conn ->
            assertFalse { lists.hasList(conn, 69) }
        }
    }
}
