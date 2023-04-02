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

class TasksDataPostgres(sourceURL: String) : TaskData {
    private val source = PGSimpleDataSource().apply { setURL(System.getenv(sourceURL)) }

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

    override fun reset() {
        File("src/main/sql/resetData.sql")
            .readText()
            .also { s->
                source.connection.use {conn->
                    conn.prepareStatement(s)
                        .executeUpdate()
                }
            }
    }

    override val users = UsersDataPostgres()
    override val tokens = TokensDataPostgres()
    override val boards = BoardsDataPostgres()
    override val lists = ListsDataPostgres()
    override val cards = CardsDataPostgres()
}
