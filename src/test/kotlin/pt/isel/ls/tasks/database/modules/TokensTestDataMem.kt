package pt.isel.ls.tasks.database.modules

import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.modules.tokens.TokensDataMem
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TokensTestDataMem {
    private val storage = TasksDataStorage()
    private val source = TasksDataMem(storage)
    private val tokens = TokensDataMem(storage)

    @Test
    fun `check is token have a user assigned`() {
        source.run { conn ->
            val res = tokens.getUserID(conn, "9f1e3d11-8c18-4cd7-93fc-985c4794cfd9")
            assertEquals(1, res)
        }
    }

    @Test
    fun `Throws an error for a nonexistent token associated with a user`() {
        source.run { conn ->
            assertFailsWith<IllegalStateException> {
                tokens.getUserID(conn, "9f1e3d11-8c18-4cd7-93fc-985c4794cfd")
            }
        }
    }

    @Test
    fun `Confirm that the token already exist`() {
        source.run { conn ->
            assertTrue { tokens.hasToken(conn, "9f1e3d11-8c18-4cd7-93fc-985c4794cfd9") }
        }
    }

    @Test
    fun `Confirm that the token do not exist`() {
        source.run { conn ->
            assertFalse { tokens.hasToken(conn, "eu20-007-fuc-y40-985c4794cfd9") }
        }
    }
}
