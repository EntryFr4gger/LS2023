package pt.isel.ls.tasks.database.modules.users

import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.modules.users.UsersDataMem
import pt.isel.ls.tasks.domain.User
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class UsersTestDataMem : UsersTestDB {
    private val storage = TasksDataStorage()
    private val source = TasksDataMem(storage)
    private val users = UsersDataMem(storage)

    @Test
    override fun `User is created correctly and with right identifier`() {
        source.execute { conn ->
            val user = User(1, "Bernardo", "bernardo@isel.pt")
            val id = users.createNewUser(conn, user.name, user.email)

            assertEquals(id, user.id)
            assertEquals(user, storage.users[id])
        }
    }

    @Test
    override fun `Throws an error if email is already in use`() {
        source.execute { conn ->
            assertFailsWith<IllegalStateException> {
                repeat(2) {
                    users.createNewUser(conn, "Bernardo", "bernas@isel.pt")
                }
            }
        }
    }

    @Test
    override fun `Gets the correct user`() {
        source.execute { conn ->
            val user = User(1, "Bernardo", "bernardo@isel.pt")
            val id = users.createNewUser(conn, user.name, user.email)

            assertEquals(user, users.getUserDetails(conn, id))
        }
    }

    @Test
    override fun `Throws an error for a nonexistent user `() {
        source.execute { conn ->
            assertFailsWith<IllegalStateException> {
                users.getUserDetails(conn, 1)
            }
        }
    }
}
