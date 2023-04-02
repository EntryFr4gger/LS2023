package pt.isel.ls.tasks.database.modules

import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.errors.NotFoundException
import pt.isel.ls.tasks.db.modules.boards.BoardsDataMem
import pt.isel.ls.tasks.domain.Board
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BoardsTestDataMem {
    private val storage = TasksDataStorage()
    private val source = TasksDataMem(storage)
    private val boards = BoardsDataMem(storage)

    @Test
    fun `Board is created correctly and with right identifier`() {
        source.run { conn ->
            val id = boards.createNewBoard(conn, "Every day", "Tomorrow is a new day")
            val boardCreated = Board(id, "Every day", "Tomorrow is a new day")
            assertEquals(boardCreated, storage.boards[id])
        }
    }


    @Test
    fun `return true if user do not exist`() {
        source.run { conn ->
            val ret = boards.addUserToBoard(conn, 1, 1)
            assertTrue { ret }
        }
    }

    @Test
    fun `Verify if the user was correctly added to the board  `() {
        source.run { conn ->
            val ret = boards.addUserToBoard(conn, 1, 2)
            assertTrue { ret }
        }
    }

    @Test
    fun `Throws an error for a nonexistent board`() {
        source.run { conn ->
            assertFailsWith<NotFoundException> {
                boards.getBoardDetails(conn, 100)
            }
        }
    }

    @Test
    fun `Gets the correct lists of a board`() {
        source.run { conn ->
            val lists = listOf(
                pt.isel.ls.tasks.domain.List(1, "Aula de LS", 1),
                pt.isel.ls.tasks.domain.List(2, "Aula de LAE", 1)
            )
            val res = boards.getAllLists(conn, 1)
            assertEquals(lists, res)
        }
    }

    @Test
    fun `Confirm that the board name already exist`() {
        source.run { conn ->
            assertTrue { boards.hasBoardName(conn, "ISEL") }
        }
    }

    @Test
    fun `Confirm that the board name do not exist`() {
        source.run { conn ->
            assertFalse { boards.hasBoardName(conn, "Mr.Nervoso") }
        }
    }

    @Test
    fun `Confirm that the board already exist`() {
        source.run { conn ->
            assertTrue { boards.hasBoard(conn, 1) }
        }
    }

    @Test
    fun `Confirm that the board do not exist`() {
        source.run { conn ->
            assertFalse { boards.hasBoard(conn, 69) }
        }
    }
}
