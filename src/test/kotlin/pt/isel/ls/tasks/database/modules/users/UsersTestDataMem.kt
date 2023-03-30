package pt.isel.ls.tasks.database.modules.users

import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.modules.users.UsersDataMem
import pt.isel.ls.tasks.domain.User
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class UsersTestDataMem  {
    private val storage = TasksDataStorage()
    private val source = TasksDataMem(storage)
    private val users = UsersDataMem(storage)

    @Test
     fun `User is created correctly and with right identifier`() {
        source.run { conn ->
            val user = User(1, "Bernardo", "bernardo@isel.pt")
            val id = users.createNewUser(conn, user.name, user.email)
            val newUser = user.copy(id=id )
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
            val user =User(3, "Godofredo", "Godofredo@outlook.pt")
            assertEquals(user, users.getUserDetails(conn, 3))
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
}
