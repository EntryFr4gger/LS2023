package pt.isel.ls.tasks.database.modules.users

import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
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
            val user = User(1, "Bernardo", "bernardo@isel.pt")
            val id = users.createNewUser(conn, user.name, user.email)
            val newUser = user.copy(id = id)
            assertEquals(newUser, storage.users[id])
        }
    }

    @Test
    fun `Throws an error if email is already in use`() {
        source.run { conn ->
            assertFailsWith<IllegalStateException> {
                repeat(2) {
                    users.createNewUser(conn, "Bernardo", "bernas@isel.pt")
                }
            }
        }
    }

    @Test
    fun `Gets the correct user`() {
        source.run { conn ->
            val user = User(3, "Godofredo", "Godofredo@outlook.pt")
            val res = users.getUserDetails(conn, 3)
            assertEquals(user, res)
        }
    }

    @Test
    fun `Throws an error for a user without  boards`() {
        source.run { conn ->
            assertFailsWith<IllegalStateException> {
                users.getUserBoards(conn, 4)
            }
        }
    }

    @Test
    fun `Verify if the user has boards if not, throws an error`() {
        source.run { conn ->
            val cboards = listOf(
                Board(1, "ISEL", "Cenas do 4 semestre do isel"),
                Board(2, "Compras", "Ida ao supermercado")
            )
            assertEquals(cboards, users.getUserBoards(conn, 2))
        }
    }

    @Test
    fun `Throws an error for a nonexistent user `() {
        source.run { conn ->
            assertFailsWith<IllegalStateException> {
                users.getUserDetails(conn, 10)
            }
        }
    }

    @Test
    fun `Confirm that the email already exist`() {
        source.run { conn ->
            assertTrue { users.hasUserEmail(conn, "UserWithNoBoard@outlook.pt") }
        }
    }

    @Test
    fun `Confirm that the email do not exist`() {
        source.run { conn ->
            assertFalse { users.hasUserEmail(conn, "keepCalmMyFriend@outlook.pt") }
        }
    }

    @Test
    fun `Confirm that the user already exist`() {
        source.run { conn ->
            assertTrue { users.hasUser(conn, 1) }
        }
    }

    @Test
    fun `Confirm that the user do not exist`() {
        source.run { conn ->
            assertFalse { users.hasUser(conn, Int.MAX_VALUE) }
        }
    }

    @Test
    fun `confirm that have a user in the board`() {
        source.run { conn ->
            assertTrue { users.hasUserInBoard(conn, 1) }
        }
    }

    @Test
    fun `confirm that do not have a user in the board`() {
        source.run { conn ->
            assertFalse { users.hasUserInBoard(conn, 4) }
        }
    }
}
