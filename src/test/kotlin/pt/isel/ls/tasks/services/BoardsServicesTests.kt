package pt.isel.ls.tasks.services

import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.domain.Board
import pt.isel.ls.tasks.domain.List
import pt.isel.ls.tasks.services.errors.ServicesError
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class BoardsServicesTests : ClearData() {
    private val storage = TasksDataStorage()
    private val source = TasksDataMem(storage)
    private val services = TaskServices(source)

    @Test
    fun `create board correctly`() {
        val id = services.boards.createNewBoard("Board", "boas", 1)
        val board = source.run { source.boards.getBoardDetails(it, id) }
        assertEquals(Board(id, "Board", "boas"), board)
    }

    @Test
    fun `create board throws InvalidArgumentException if name is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.boards.createNewBoard("B", "boas", 1)
        }
    }

    @Test
    fun `create board throws InvalidArgumentException if description is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.boards.createNewBoard("Board", "", 1)
        }
    }

    @Test
    fun `create board throws AuthenticationException if user doesn't exist`() {
        assertFailsWith<ServicesError.AuthenticationException> {
            services.boards.createNewBoard("Board", "boas", Int.MAX_VALUE)
        }
    }

    @Test
    fun `create board throws AlreadyExistsException if board already exist`() {
        source.run { source.boards.createNewBoard(it, "Board", "Board") }
        assertFailsWith<ServicesError.AlreadyExistsException> {
            services.boards.createNewBoard("Board", "Board", 1)
        }
    }

    @Test
    fun `add user to board correctly`() {
        source.run {
            val userId = source.users.createNewUser(it, "Armandio", "Armandio@gmail.com")
            val userIdTest = source.users.createNewUser(it, "Armandio", "dio@gmail.com")
            val boardId = source.boards.createNewBoard(it, "Armandio", "sadsad")
            source.boards.addUserToBoard(it, userId, boardId)
            assertTrue(services.boards.addUserToBoard(userIdTest, boardId, userId))
            assertTrue(storage.userBoard[userIdTest]!!.contains(boardId))
        }
    }

    @Test
    fun `add user to board throws InvalidArgumentException if user id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.boards.addUserToBoard(-2, 1, 1)
        }
    }

    @Test
    fun `add user to board throws InvalidArgumentException if board id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.boards.addUserToBoard(1, -2, 1)
        }
    }

    @Test
    fun `add user to board throws AuthorizationException if user don't have permission`() {
        assertFailsWith<ServicesError.AuthorizationException> {
            services.boards.addUserToBoard(1, 1, 3)
        }
    }

    @Test
    fun `get board details correctly`() {
        source.run {
            val userId = source.users.createNewUser(it, "Armandio", "Armandio@gmail.com")
            val boardId = source.boards.createNewBoard(it, "Armandio", "sadsad")
            source.boards.addUserToBoard(it, userId, boardId)
            assertEquals(Board(boardId, "Armandio", "sadsad"), services.boards.getBoardDetails(boardId, userId, fields))
        }
    }

    @Test
    fun `get board details throws InvalidArgumentException if board id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.boards.getBoardDetails(-2, 1, fields)
        }
    }

    @Test
    fun `get board details throws AuthorizationException if user don't have permission`() {
        assertFailsWith<ServicesError.AuthorizationException> {
            services.boards.getBoardDetails(1, 3, fields)
        }
    }

    @Test
    fun `get all lists correctly`() {
        source.run {
            val userId = source.users.createNewUser(it, "Armandio", "Armandio@gmail.com")
            val boardId = source.boards.createNewBoard(it, "Armandio", "sadsad")
            val listId = source.lists.createList(it, "list", boardId)
            source.boards.addUserToBoard(it, userId, boardId)
            assertEquals(listOf(List(listId, "list", boardId)), services.boards.getAllLists(boardId, 1, 1, userId))
        }
    }

    @Test
    fun `get all lists throws InvalidArgumentException if board id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.boards.getAllLists(-2, 1, 1, 1)
        }
    }

    @Test
    fun `get all lists throws AuthorizationException if user don't have permission`() {
        assertFailsWith<ServicesError.AuthorizationException> {
            services.boards.getAllLists(1, 1, 1, 3)
        }
    }
}
