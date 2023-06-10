package pt.isel.ls.tasks.database

import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.errors.NotFoundException
import pt.isel.ls.tasks.db.modules.boards.BoardsDataMem
import pt.isel.ls.tasks.domain.Board
import pt.isel.ls.tasks.domain.User
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
                Board(1, "ISEL", "Cenas do 4 semestre do isel"),
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
                    pt.isel.ls.tasks.domain.List(1, "Aula de LS", 1),
                    pt.isel.ls.tasks.domain.List(2, "Aula de LAE", 1)
                ),
                boards.getAllLists(conn, 1, 1, 1)
            )
        }
    }

    @Test
    fun `Gets the correct lists of a users in a board`() {
        source.run { conn ->
            assertEquals(
                listOf(
                    User(
                        1,
                        "Admin",
                        "Admin@gmail.com",
                        "6593D31A65175D624AFC703A4070DB550D4C7B91C795E431DA9A69E52C1F313E"
                    ),
                    User(
                        2,
                        "Rafa",
                        "rafaelDCosta@outlook.com",
                        "D5989C7FFC36711AF4BD46606D051ECD70A45C581E85428C8B129722C260EBEE"
                    )
                ),
                boards.getBoardUsers(conn, 2, 1, 1)
            )
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
