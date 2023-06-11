package pt.isel.ls.tasks.services

import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.domain.Board
import pt.isel.ls.tasks.domain.List
import pt.isel.ls.tasks.services.errors.ServicesError
import pt.isel.ls.tasks.services.modules.boards.response.BoardDetailsResponse
import java.sql.SQLException
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class BoardsServicesTests : ClearData() {
    private val storage = TasksDataStorage()
    private val source = TasksDataMem(storage)
    private val services = TaskServices(source)

    @Test
    fun `Create board correctly`() {
        val id = services.boards.createNewBoard("Board", "boas", 1)
        val board = source.run { source.boards.getBoardDetails(it, id) }
        assertEquals(Board(id, "Board", "boas"), board)
    }

    @Test
    fun `Create board throws InvalidArgumentException if name is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.boards.createNewBoard("B", "boas", 1)
        }
    }

    @Test
    fun `Create board throws InvalidArgumentException if description is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.boards.createNewBoard("Board", "", 1)
        }
    }

    @Test
    fun `Create board throws AuthenticationException if user doesn't exist`() {
        assertFailsWith<ServicesError.AuthenticationException> {
            services.boards.createNewBoard("Board", "boas", Int.MAX_VALUE)
        }
    }

    @Test
    fun `Create board throws AlreadyExistsException if board already exist`() {
        source.run { source.boards.createNewBoard(it, "Board", "Board") }
        assertFailsWith<ServicesError.AlreadyExistsException> {
            services.boards.createNewBoard("Board", "Board", 1)
        }
    }

    @Test
    fun `Add user to board correctly`() {
        source.run {
            val userId = source.users.createNewUser(it, "Armandio", "Armandio@gmail.com", "Adsfs123&")
            val userIdTest = source.users.createNewUser(it, "Armandio", "dio@gmail.com", "Adsfs123&")
            val boardId = source.boards.createNewBoard(it, "Armandio", "sadsad")
            source.boards.addUserToBoard(it, userId, boardId)
            assertTrue(services.boards.addUserToBoard(userIdTest, boardId, userId))
            assertTrue(storage.userBoard[userIdTest]!!.contains(boardId))
        }
    }

    @Test
    fun `Add user to board throws InvalidArgumentException if user id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.boards.addUserToBoard(-2, 1, 1)
        }
    }

    @Test
    fun `Add user to board throws InvalidArgumentException if board id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.boards.addUserToBoard(1, -2, 1)
        }
    }

    @Test
    fun `Add user to board throws AuthorizationException if user don't have permission`() {
        assertFailsWith<ServicesError.AuthorizationException> {
            services.boards.addUserToBoard(1, 1, 3)
        }
    }

    @Test
    fun `Get board details correctly`() {
        source.run {
            val userId = source.users.createNewUser(it, "Armandio", "Armandio@gmail.com", "Adsfs123&")
            val boardId = source.boards.createNewBoard(it, "Armandio", "sadsad")
            source.boards.addUserToBoard(it, userId, boardId)
            assertEquals(
                BoardDetailsResponse(Board(boardId, "Armandio", "sadsad"), null),
                services.boards.getBoardDetails(boardId, userId, emptyList())
            )
        }
    }

    @Test
    fun `Get board details throws InvalidArgumentException if board id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.boards.getBoardDetails(-2, 1, emptyList())
        }
    }

    @Test
    fun `Get board details throws AuthorizationException if user don't have permission`() {
        assertFailsWith<ServicesError.AuthorizationException> {
            services.boards.getBoardDetails(1, 3, emptyList())
        }
    }

    @Test
    fun `Get all lists correctly`() {
        source.run {
            val userId = source.users.createNewUser(it, "Armandio", "Armandio@gmail.com", "Adsfs123&")
            val boardId = source.boards.createNewBoard(it, "Armandio", "sadsad")
            val listId = source.lists.createList(it, "list", boardId)
            source.boards.addUserToBoard(it, userId, boardId)
            assertEquals(listOf(List(listId, "list", boardId)), services.boards.getAllLists(boardId, 1, 1, userId))
        }
    }

    @Test
    fun `Get all lists throws InvalidArgumentException if board id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.boards.getAllLists(-2, 1, 1, 1)
        }
    }

    @Test
    fun `Get all lists throws AuthorizationException if user don't have permission`() {
        assertFailsWith<ServicesError.AuthorizationException> {
            services.boards.getAllLists(1, 1, 1, 3)
        }
    }

    @Test
    fun `Get all cards archived`() {
        source.run {
            assertEquals(listOf(storage.cards[5]), services.boards.getAllCards(1, 0, 1, 1, true))
        }
    }

    @Test
    fun `Get all cards correctly`() {
        source.run {
            assertEquals(listOf(storage.cards[1]), services.boards.getAllCards(1, 0, 1, 1, false))
        }
    }

    @Test
    fun `Get all cards throws InvalidArgumentException if board id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.boards.getAllCards(-2, 1, 1, 1, true)
        }
    }

    @Test
    fun `Get all cards throws AuthorizationException if user don't have permission`() {
        assertFailsWith<ServicesError.AuthorizationException> {
            services.boards.getAllCards(1, 1, 1, 3, true)
        }
    }

    @Test
    fun `Get board users correctly`() {
        source.run {
            assertEquals(listOf(storage.users[1]), services.boards.getBoardUsers(2, 0, 1, 1))
        }
    }

    @Test
    fun `Get board users throws InvalidArgumentException if board id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.boards.getBoardUsers(-2, 1, 1, 1)
        }
    }

    @Test
    fun `Get board users throws AuthorizationException if user don't have permission`() {
        assertFailsWith<ServicesError.AuthorizationException> {
            services.boards.getBoardUsers(1, 1, 1, 3)
        }
    }

    @Test
    fun `Search board correctly`() {
        source.run {
            assertEquals(listOf(storage.boards[2]), services.boards.searchBoards(0, 1, "com", 1))
        }
    }

    @Test
    fun `Search board throws InvalidArgumentException if board id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.boards.searchBoards(1, 1, "1", -1)
        }
    }

    @Test
    fun `Delete a board`() {
        services.boards.deleteBoard(1, 1)
        assertTrue { storage.boards[1] == null }
    }

    @Test
    fun `Delete board throws InvalidArgumentException if board id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.boards.deleteBoard(-1, Int.MAX_VALUE)
        }
    }

    @Test
    fun `Delete board throws AuthorizationException if user don't have permission`() {
        assertFailsWith<ServicesError.AuthorizationException> {
            services.boards.deleteBoard(1, 3)
        }
    }
}
