package pt.isel.ls.tasks.db

import org.postgresql.ds.PGSimpleDataSource
import pt.isel.ls.tasks.db.modules.boards.BoardsDataPostgres
import pt.isel.ls.tasks.db.modules.cards.CardsDataPostgres
import pt.isel.ls.tasks.db.modules.lists.ListsDataPostgres
import pt.isel.ls.tasks.db.modules.tokens.TokensDataPostgres
import pt.isel.ls.tasks.db.modules.users.UsersDataPostgres
import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.db.transactionManager.TransactionManagerDP
import java.io.File
import java.sql.SQLException

/**
 * Data repository implementation for tasks-related data using a PostgreSQL database.
 *
 * @param sourceURL The URL of the PostgreSQL database.
 */
class TasksDataPostgres(sourceURL: String) : TaskData {
    private val source = PGSimpleDataSource().apply { setURL(System.getenv(sourceURL)) }

    /**
     * Runs a function within a transaction context.
     *
     * @param function The function to be executed within the transaction context.
     *
     * @return The result of the function.
     */
    override fun <R> run(function: (TransactionManager) -> R): R {
        val conn = try {
            source.connection
        } catch (e: SQLException) {
            throw e
        }
        conn.autoCommit = false

        return try {
            function(TransactionManagerDP(conn)).also { conn.commit() }
        } catch (e: Error) {
            conn.rollback()
            throw e
        } finally {
            conn.close()
        }
    }

    /**
     * Resets the data storage by executing an SQL script file.
     */
    override fun reset() {
        File("src/main/sql/resetData.sql")
            .readText()
            .also { s ->
                source.connection.use { conn ->
                    conn.prepareStatement(s)
                        .executeUpdate()
                }
            }
    }

    /**
     * Provides access to users-related data operations.
     */
    override val users = UsersDataPostgres()

    /**
     * Provides access to tokens-related data operations.
     */
    override val tokens = TokensDataPostgres()

    /**
     * Provides access to boards-related data operations.
     */
    override val boards = BoardsDataPostgres()

    /**
     * Provides access to lists-related data operations.
     */
    override val lists = ListsDataPostgres()

    /**
     * Provides access to cards-related data operations.
     */
    override val cards = CardsDataPostgres()
}
