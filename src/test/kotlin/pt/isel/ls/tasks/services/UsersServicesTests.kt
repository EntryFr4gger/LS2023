package pt.isel.ls.tasks.services

import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.domain.Board
import pt.isel.ls.tasks.domain.User
import pt.isel.ls.tasks.services.errors.ServicesError
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class UsersServicesTests : ClearData() {
    private val storage = TasksDataStorage()
    private val source = TasksDataMem(storage)
    private val services = TaskServices(source)

    @Test
    fun `create user correctly`() {
        val id = services.users.createNewUser("Arlindo", "arlindo@gmail.com")
        val user = source.run { source.users.getUserDetails(it, id.id) }
        assertEquals(User(id.id, "Arlindo", "arlindo@gmail.com"), user)
    }

    @Test
    fun `create user throws InvalidArgumentException if name is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.users.createNewUser("A", "arlindo@gmail.com")
        }
    }

    @Test
    fun `create user throws InvalidArgumentException if email is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.users.createNewUser("Arlindo", "@@gmail.com")
        }
    }

    @Test
    fun `create user throws AlreadyExistsException if email already exist`() {
        source.run { source.users.createNewUser(it, "Armandio", "Armandio@gmail.com") }
        assertFailsWith<ServicesError.AlreadyExistsException> {
            services.users.createNewUser("Armandio", "Armandio@gmail.com")
        }
    }

    @Test
    fun `get user details correctly`() {
        val id = source.run { source.users.createNewUser(it, "Armandio", "Armandio@gmail.com") }
        assertEquals(User(id, "Armandio", "Armandio@gmail.com"), services.users.getUserDetails(id))
    }

    @Test
    fun `get user details throws InvalidArgumentException if id isn't valid`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.users.getUserDetails(-2)
        }
    }

    @Test
    fun `get user boards correctly`() {
        source.run {
            val userId = source.users.createNewUser(it, "Armandio", "Armandio@gmail.com")
            val boardId = source.boards.createNewBoard(it, "Armandio", "sadsad")
            source.boards.addUserToBoard(it, userId, boardId)
            assertEquals(listOf(Board(boardId, "Armandio", "sadsad")), services.users.getUserBoards(userId))
        }
    }

    @Test
    fun `get user boards throws InvalidArgumentException if id isn't valid`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.users.getUserBoards(-2)
        }
    }
}