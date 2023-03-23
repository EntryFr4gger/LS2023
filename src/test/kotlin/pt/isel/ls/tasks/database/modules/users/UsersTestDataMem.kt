package pt.isel.ls.tasks.database.modules.users

import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.modules.users.UsersDataMem
import pt.isel.ls.tasks.domain.User
import java.sql.Connection
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class UsersTestDataMem: UsersTestDB {
    private val storage = TasksDataStorage()
    private val source = UsersDataMem(storage)

    @Test
    override fun `User is created correctly and with right identifier`() {
        val user = User(1, "Bernardo", "bernardo@isel.pt")
        val id = source.createNewUser(null as Connection, user.name, user.email)

        assertEquals(id, user.id)
        assertEquals(user, storage.users[id])
    }

    @Test
    override fun `Throws an error if email is already in use`() {
        assertFailsWith<IllegalStateException> {
            repeat(2){
                source.createNewUser(null as Connection, "Bernardo", "bernas@isel.pt")
            }
        }
    }

    @Test
    override fun `Gets the correct user`() {
        val user = User(1, "Bernardo", "bernardo@isel.pt")
        val id = source.createNewUser(null as Connection, user.name, user.email)

        assertEquals(user, source.getUserDetails(null as Connection, id))
    }

    @Test
    override fun `Throws an error for a nonexistent user `() {
        assertFailsWith<IllegalStateException> {
            source.getUserDetails(null as Connection, 1)
        }
    }
}