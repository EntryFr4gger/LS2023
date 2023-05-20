package pt.isel.ls.tasks.database.modules

import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.errors.NotFoundException
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
            val id = lists.createList(conn, "Study", 1)
            assertEquals(
                List(id, "Study", 1),
                storage.lists[id]
            )
        }
    }

    @Test
    fun `Get the correct list details`() {
        source.run { conn ->
            assertEquals(
                List(1, "Aula de LS", 1),
                lists.getListDetails(conn, 1)
            )
        }
    }

    @Test
    fun `Throws an error for a nonexistent lists`() {
        source.run { conn ->
            assertFailsWith<NotFoundException> {
                lists.getListDetails(conn, Int.MAX_VALUE)
            }
        }
    }

    @Test
    fun `get all cards in the same list`() {
        source.run { conn ->
            assertEquals(
                listOf(
                    Card(
                        1,
                        "Phase 1",
                        "Entrega da parte 1 do trabalho de LS",
                        LocalDate(2023, 4, 2),
                        1,
                        1,
                        1
                    )
                ),
                lists.getAllCards(conn, 1, 1, 3)
            )
        }
    }

    @Test
    fun `delete list from a board`() {
        source.run { conn ->
            lists.deleteList(conn, 1)
            assertFalse { lists.hasList(conn, 1) }
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
