package pt.isel.ls.tasks.database.modules.token

import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.modules.lists.ListsDataMem
import pt.isel.ls.tasks.db.modules.tokens.TokensDataMem
import pt.isel.ls.tasks.db.modules.users.UsersDataMem
import pt.isel.ls.tasks.domain.User
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class TokensTestDataMem {
    private val storage = TasksDataStorage()
    private val source = TasksDataMem(storage)
    private val tokens = TokensDataMem(storage)

    @Test
    fun `check is token have a user assigned`(){
        source.run { conn ->
            val res = tokens.getUserID(conn,"9f1e3d11-8c18-4cd7-93fc-985c4794cfd9")
            assertEquals(1, res)
        }
    }
    @Test
    fun `Throws an error for a nonexistent token associated with a user`(){
        source.run { conn ->
            assertFailsWith<IllegalStateException> {
                    tokens.getUserID(conn,"9f1e3d11-8c18-4cd7-93fc-985c4794cfd")
            }
        }
    }

}