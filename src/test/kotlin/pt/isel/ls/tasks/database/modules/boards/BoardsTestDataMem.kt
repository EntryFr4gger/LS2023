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
class BoardsTestDataMem {
    private val storage = TasksDataStorage()
    private val source = TasksDataMem(storage)
    private val boards = BoardsDataMem(storage)

    @Test
    fun `Board is created correctly and with right identifier`() {
        source.execute { conn ->
            val id = boards.createNewBoard(conn,"Every day","Tomorrow is a new day")
            val boardCreated = Board (id, "Every day","Tomorrow is a new day")
            assertEquals(boardCreated, storage.boards[id])
        }
    }
    @Test
    fun `Throws an error if board do not exist`() {
        source.execute { conn ->
            val board = Board(1,"Every day", "Tomorrow is a new day")
            val user = User(1, "Bernardo", "bernardo@isel.pt")
            val ret = boards.addUserToBoard(conn, user.id, board.id)
            assertEquals(ret, user.id)
        }
    }
    @Test
    fun `Verify if the user has boards if not, throws an error`() {
        source.execute { conn ->
            val cboards  = listOf(
                Board(1,"Every day", "Tomorrow is a new day"),
                Board(2,"Every morning", "Tomorrow is a new morning"),
                Board(3,"Every night", "Tomorrow is a new night"),
            )
            val ids = cboards.map { boards.createNewBoard(conn, it.name, it.description) }
            val user = User(1, "Bernardo", "bernardo@isel.pt")
            cboards.map{it.id}.also {storage.userBoard[user.id] = it }
            assertEquals(cboards.map{it.id}, ids)
            assertEquals(cboards, boards.getUserBoards(conn, user.id))
        }
    }
    @Test
    fun `Verify if the user was correctly added to the board   `() {
        source.execute { conn ->
            val board = Board(1,"Every day", "Tomorrow is a new day")
            val user = User(1, "Bernardo", "bernardo@isel.pt")
            val ret = boards.addUserToBoard(conn, user.id, board.id)
            assertEquals(ret, user.id)
        }
    }
}