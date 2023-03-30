package pt.isel.ls.tasks.database.modules.boards

import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.modules.boards.BoardsDataMem
import pt.isel.ls.tasks.domain.Board
import pt.isel.ls.tasks.domain.User
import pt.isel.ls.tasks.domain.UserBoard
import java.sql.Connection
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
            val id = boards.createNewBoard(conn,"Every day","Tomorrow is a new day")
            val boardCreated = Board (id, "Every day","Tomorrow is a new day")
            assertEquals(boardCreated, storage.boards[id])
        }
    }
    @Test
    fun `return -1 if user do not exist`() {
        source.run { conn ->
            val ret = boards.addUserToBoard(conn, 10, 1)
            assertEquals(-1, ret)
        }
    }
    @Test
    fun `return 1 if user do not exist`() {
        source.run { conn ->
            val ret = boards.addUserToBoard(conn, 1, 1)
            assertEquals(1, ret)
        }
    }
    @Test
    fun `Verify if the user has boards if not, throws an error`() {
        source.run { conn ->
            val cboards  = listOf(
                Board(1, "ISEL", "Cenas do 4 semestre do isel"),
                Board(2, "Compras", "Ida ao supermercado")
            )
            assertEquals(cboards, boards.getUserBoards(conn, 2))
        }
    }
    @Test
    fun `Verify if the user was correctly added to the board  `() {
        source.run { conn ->
            val ret = boards.addUserToBoard(conn, 1, 2)
            assertEquals(1,ret)
        }
    }

    @Test
    fun `Throws an error for a nonexistent board`(){
        source.run { conn ->
            assertFailsWith<IllegalStateException> {
                boards.getBoardDetails(conn,100)
            }
        }
    }

    @Test
    fun `Throws an error for a user without  boards`(){
        source.run { conn ->
            assertFailsWith<IllegalStateException> {
                boards.getUserBoards(conn,4)
            }
        }
    }

    @Test
    fun `Confirm that the board name already exist`(){
        source.run {conn ->
            assertTrue { boards.isNewName(conn, "ISEL") }
        }
    }

    @Test
    fun `Confirm that the board name do not exist`(){
        source.run {conn ->
            assertFalse { boards.isNewName(conn, "Mr.Nervoso") }
        }
    }

    @Test
    fun `Confirm that the board already exist`(){
        source.run {conn ->
            assertTrue { boards.hasBoard(conn, 1) }
        }
    }

    @Test
    fun `Confirm that the board do not exist`(){
        source.run {conn ->
            assertFalse { boards.hasBoard(conn, 69) }
        }
    }

    @Test
    fun `confirm that have a user in the board`(){
        source.run {conn ->
            assertTrue { boards.hasUserInBoard(conn, 1) }
        }
    }

    @Test
    fun `confirm that do not have a user in the board`(){
        source.run {conn ->
            assertFalse { boards.hasUserInBoard(conn, 4) }
        }
    }
}