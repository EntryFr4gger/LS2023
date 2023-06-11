package pt.isel.ls.tasks.database

import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.errors.NotFoundException
import pt.isel.ls.tasks.db.modules.lists.ListsDataMem
import pt.isel.ls.tasks.domain.List
import java.sql.SQLException
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
                storage.lists[1],
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
                listOf(storage.cards[4]),
                lists.getAllCards(conn, 3, 1, 1)
            )
        }
    }

    @Test
    fun `Delete list from a board`() {
        source.run { conn ->
            lists.deleteList(conn, 1)
            assertTrue { storage.lists[1] == null }
        }
    }

    @Test
    fun `Delete unsuccessful`() {
        source.run { conn ->
            assertFailsWith<SQLException> {
                lists.deleteList(conn, Int.MAX_VALUE)
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
