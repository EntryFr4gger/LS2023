package pt.isel.ls.tasks.services

import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.domain.User
import pt.isel.ls.tasks.services.errors.ServicesError
import pt.isel.ls.tasks.services.modules.users.responses.UserInfoResponse
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class UsersServicesTests : ClearData() {
    private val storage = TasksDataStorage()
    private val source = TasksDataMem(storage)
    private val services = TaskServices(source)

    @Test
    fun `Create user correctly`() {
        val id = services.users.createNewUser("Arlindo", "arlindo@gmail.com", "Adsfs123&")
        assertEquals(
            User(
                id.id,
                "Arlindo",
                "arlindo@gmail.com",
                "D12CC6817061AA42A23AE259DED1F419C45D03DB2C2EE02ACC4784A9761D781A"
            ),
            storage.users[id.id]
        )
    }

    @Test
    fun `Create user throws InvalidArgumentException if name is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.users.createNewUser("A", "arlindo@gmail.com", "Adsfs123&")
        }
    }

    @Test
    fun `Create user throws InvalidArgumentException if email is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.users.createNewUser("Arlindo", "@@gmail.com", "Adsfs123&")
        }
    }

    @Test
    fun `Create user throws AlreadyExistsException if email already exist`() {
        assertFailsWith<ServicesError.AlreadyExistsException> {
            services.users.createNewUser("Armandio", "Admin@gmail.com", "Adsfs123&")
        }
    }

    @Test
    fun `Login user correctly`() {
        val id = services.users.loginUser("Admin@gmail.com", "Admin123&")
        assertEquals(
            UserInfoResponse(1, "9f1e3d11-8c18-4cd7-93fc-985c4794cfd9"),
            id
        )
    }

    @Test
    fun `Login user throws InvalidArgumentException if email is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.users.loginUser("A", "Admin123&")
        }
    }

    @Test
    fun `Let user details correctly`() {
        assertEquals(storage.users[1], services.users.getUserDetails(1))
    }

    @Test
    fun `Get user details throws InvalidArgumentException if id isn't valid`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.users.getUserDetails(-2)
        }
    }

    @Test
    fun `Get user boards correctly`() {
        source.run {
            assertEquals(
                listOf(
                    storage.boards[1],
                    storage.boards[2]
                ),
                services.users.getUserBoards(2, 0, 2)
            )
        }
    }

    @Test
    fun `Get user boards throws InvalidArgumentException if id isn't valid`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.users.getUserBoards(-2, 1, 1)
        }
    }

    @Test
    fun `Get all users that are not on that board`() {
        assertEquals(
            listOf(storage.users[2], storage.users[4]),
            services.users.getAllUsersNotInBoard(3, 1)
        )
    }

    @Test
    fun `Get all users throws InvalidArgumentException if id isn't valid`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.users.getAllUsersNotInBoard(-1, 1)
        }
    }

    @Test
    fun `Get all users throws AuthorizationException if user don't have permission`() {
        assertFailsWith<ServicesError.AuthorizationException> {
            services.users.getAllUsersNotInBoard(3, 2)
        }
    }
}
