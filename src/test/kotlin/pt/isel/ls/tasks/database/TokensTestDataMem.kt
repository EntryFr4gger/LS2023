package pt.isel.ls.tasks.database

import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.errors.NotFoundException
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
    fun `Creates a token for a new user`() {
        source.run { conn ->
            tokens.createNewToken(conn, "b606bd17-aac8-470e-a539-fe590944b1f6", 5)
            assertTrue(storage.tokens["b606bd17-aac8-470e-a539-fe590944b1f6"] != null)
        }
    }

    @Test
    fun `Check is token have a user assigned`() {
        source.run { conn ->
            assertEquals(
                1,
                tokens.getUserID(conn, "9f1e3d11-8c18-4cd7-93fc-985c4794cfd9")
            )
        }
    }

    @Test
    fun `Throws an error for a nonexistent token associated with a user`() {
        source.run { conn ->
            assertFailsWith<NotFoundException> {
                tokens.getUserID(conn, "9f1e3d11-8c18-4cd7-93fc-985c4794cfd")
            }
        }
    }

    @Test
    fun `Get the correct token given a userId`() {
        source.run { conn ->
            assertEquals(
                "9f1e3d11-8c18-4cd7-93fc-985c4794cfd9",
                tokens.getUserToken(conn, 1)
            )
        }
    }

    @Test
    fun `Throws an error for a nonexistent userId associated with a token`() {
        source.run { conn ->
            assertFailsWith<NotFoundException> {
                tokens.getUserToken(conn, Int.MAX_VALUE)
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
