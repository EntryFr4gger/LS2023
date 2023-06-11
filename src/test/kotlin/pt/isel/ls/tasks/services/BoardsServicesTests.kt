package pt.isel.ls.tasks.services

import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.domain.Board
import pt.isel.ls.tasks.services.errors.ServicesError
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
        assertEquals(
            Board(id, "Board", "boas"),
            storage.boards[id]
        )
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
        assertFailsWith<ServicesError.AlreadyExistsException> {
            services.boards.createNewBoard("ISEL", "Board", 1)
        }
    }

    @Test
    fun `Add user to board correctly`() {
        services.boards.addUserToBoard(3,1, 1)
        assertTrue(storage.userBoard[3]!!.contains(1))
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
        assertEquals(
            storage.boards[1],
            services.boards.getBoardDetails(1, 1, emptyList()).board
        )
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
        assertEquals(
            listOf(
                storage.lists[1],
                storage.lists[2]
            ),
            services.boards.getAllLists(1, 0, 2, 1)
        )
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
