package pt.isel.ls.tasks.database

import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.errors.NotFoundException
import pt.isel.ls.tasks.db.modules.boards.BoardsDataMem
import pt.isel.ls.tasks.domain.Board
import java.sql.SQLException
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
            assertEquals(
                Board(id, "Every day", "Tomorrow is a new day"),
                storage.boards[id]
            )
        }
    }

    @Test
    fun `Verify if the user was correctly added to the board`() {
        source.run { conn ->
            assertTrue {
                boards.addUserToBoard(conn, 1, 2)
            }
        }
    }

    @Test
    fun `Verify if the board details are correct`() {
        source.run { conn ->
            assertEquals(
                storage.boards[1],
                boards.getBoardDetails(conn, 1)
            )
        }
    }

    @Test
    fun `Throws an error for a nonexistent board`() {
        source.run { conn ->
            assertFailsWith<NotFoundException> {
                boards.getBoardDetails(conn, Int.MAX_VALUE)
            }
        }
    }

    @Test
    fun `Gets the correct lists of a board`() {
        source.run { conn ->
            assertEquals(
                listOf(
                    storage.lists[1],
                    storage.lists[2]
                ),
                boards.getAllLists(conn, 1, 0, 2)
            )
        }
    }

    @Test
    fun `Get the correct cards of a board that are Archived`() {
        source.run { conn ->
            assertEquals(
                listOf(
                    storage.cards[5],
                ),
                boards.getAllCards(conn, 1, 0, 1, true)
            )
        }
    }

    @Test
    fun `Get the correct cards of a board`() {
        source.run { conn ->
            assertEquals(
                listOf(
                    storage.cards[1],
                    storage.cards[2]
                ),
                boards.getAllCards(conn, 1, 0, 2, false)
            )
        }
    }

    @Test
    fun `Gets the correct lists of a users in a board`() {
        source.run { conn ->
            assertEquals(
                listOf(
                    storage.users[1],
                    storage.users[2]
                ),
                boards.getBoardUsers(conn, 2, 0, 2)
            )
        }
    }

    @Test
    fun `Search the correct boards`() {
        source.run { conn ->
            assertEquals(
                listOf(
                    storage.boards[2]
                ),
                boards.searchBoards(conn, 0, 1, "com", 1)
            )
        }
    }

    @Test
    fun `Delete a board`() {
        source.run { conn ->
            boards.deleteBoard(conn, 1)
            assertTrue { storage.boards[1] == null }
        }
    }

    @Test
    fun `Delete unsuccessful`() {
        source.run { conn ->
            assertFailsWith<SQLException> {
                boards.deleteBoard(conn, Int.MAX_VALUE)
            }
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
