package pt.isel.ls.tasks.database

import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.errors.NotFoundException
import pt.isel.ls.tasks.db.modules.users.UsersDataMem
import pt.isel.ls.tasks.domain.Board
import pt.isel.ls.tasks.domain.User
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class UsersTestDataMem {
    private val storage = TasksDataStorage()
    private val source = TasksDataMem(storage)
    private val users = UsersDataMem(storage)

    @Test
    fun `User is created correctly and with right identifier`() {
        source.run { conn ->
            val id = users.createNewUser(conn, "Bernardo", "bernardo@isel.pt", "Adsfs123&")
            assertEquals(
                User(id, "Bernardo", "bernardo@isel.pt", "Adsfs123&"),
                storage.users[id]
            )
        }
    }

    @Test
    fun `Gets the correct user`() {
        source.run { conn ->
            assertEquals(
                User(3, "Godofredo", "Godofredo@outlook.pt", "Adsfs123&"),
                users.getUserDetails(conn, 3)
            )
        }
    }

    @Test
    fun `Throws an error for a nonexistent user `() {
        source.run { conn ->
            assertFailsWith<NotFoundException> {
                users.getUserDetails(conn, Int.MAX_VALUE)
            }
        }
    }

    @Test
    fun `Gets User Boards`() {
        source.run { conn ->
            val cboards = listOf(
                Board(1, "ISEL", "Cenas do 4 semestre do isel"),
                Board(2, "Compras", "Ida ao supermercado")
            )
            assertEquals(cboards, users.getUserBoards(conn, 1, 1, 2))
        }
    }

    @Test
    fun `Verify that the user do not have boards`() {
        source.run { conn ->
            assertEquals(emptyList(), users.getUserBoards(conn, 1, 1, Int.MAX_VALUE))
        }
    }

    @Test
    fun `Confirm that the email do not exist`() {
        source.run { conn ->
            assertFalse { users.hasUserEmail(conn, "keepCalmMyFriend@outlook.pt") }
        }
    }

    @Test
    fun `Confirm that the email already exist`() {
        source.run { conn ->
            assertTrue { users.hasUserEmail(conn, "UserWithNoBoard@outlook.pt") }
        }
    }

    @Test
    fun `Confirm that the user do not exist`() {
        source.run { conn ->
            assertFalse { users.hasUser(conn, Int.MAX_VALUE) }
        }
    }

    @Test
    fun `Confirm that the user already exist`() {
        source.run { conn ->
            assertTrue { users.hasUser(conn, 1) }
        }
    }

    @Test
    fun `Confirm that have a user in the board`() {
        source.run { conn ->
            assertTrue { users.hasUserInBoard(conn, 1) }
        }
    }

    @Test
    fun `Confirm that do not have a user in the board`() {
        source.run { conn ->
            assertFalse { users.hasUserInBoard(conn, 4) }
        }
    }

    @Test
    fun `Validate request Board is successfully`() {
        source.run { conn ->
            assertTrue { users.validateResquestBoard(conn, 1, 1) }
        }
    }

    @Test
    fun `Validate request Board isn't successfully with wrong boardId`() {
        source.run { conn ->
            assertFalse { users.validateResquestBoard(conn, Int.MAX_VALUE, 1) }
        }
    }

    @Test
    fun `Validate request Board isn't successfully with wrong requestId`() {
        source.run { conn ->
            assertFalse { users.validateResquestBoard(conn, 1, Int.MAX_VALUE) }
        }
    }

    @Test
    fun `Validate request Card is successfully`() {
        source.run { conn ->
            assertTrue { users.validateResquestCard(conn, 1, 1) }
        }
    }

    @Test
    fun `Validate request Card is isn't successfully with wrong cardId`() {
        source.run { conn ->
            assertFalse { users.validateResquestCard(conn, Int.MAX_VALUE, 1) }
        }
    }

    @Test
    fun `Validate request Card isn't successfully with wrong requestId`() {
        source.run { conn ->
            assertFalse { users.validateResquestCard(conn, 1, Int.MAX_VALUE) }
        }
    }

    @Test
    fun `Validate request List is successfully`() {
        source.run { conn ->
            assertTrue { users.validateResquestList(conn, 1, 1) }
        }
    }

    @Test
    fun `Validate request List is isn't successfully with wrong ListId`() {
        source.run { conn ->
            assertFalse { users.validateResquestList(conn, Int.MAX_VALUE, 1) }
        }
    }

    @Test
    fun `Validate request List isn't successfully with wrong RequestId`() {
        source.run { conn ->
            assertFalse { users.validateResquestList(conn, 1, Int.MAX_VALUE) }
        }
    }
}
