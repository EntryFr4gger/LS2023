package pt.isel.ls.tasks.db

import org.postgresql.ds.PGSimpleDataSource
import pt.isel.ls.tasks.db.modules.boards.BoardsDB
import pt.isel.ls.tasks.db.modules.boards.BoardsDataPostgres
import pt.isel.ls.tasks.db.modules.cards.CardsDB
import pt.isel.ls.tasks.db.modules.cards.CardsDataPostgres
import pt.isel.ls.tasks.db.modules.lists.ListsDB
import pt.isel.ls.tasks.db.modules.lists.ListsDataPostgres
import pt.isel.ls.tasks.db.modules.users.UsersDB
import pt.isel.ls.tasks.db.modules.users.UsersDataPostgres
import java.sql.Connection
import java.sql.SQLException


class TasksDataPostgres(sourceURL: String): TaskData {
    private val source = PGSimpleDataSource().apply { setURL(System.getenv(sourceURL)) }

    override fun <R> execute(func: (Connection) -> R): R {
        val conn = try {
            source.connection
        } catch (e: SQLException){
            throw e
        }
        conn.autoCommit = false

        return try {
            func(conn).also { conn.commit() }
        }catch (e: Error){
            conn.rollback()
            throw e
        }finally {
            conn.close()
        }
    }


    override val users: UsersDB = UsersDataPostgres()
    override val boards: BoardsDB = BoardsDataPostgres()
    override val lists: ListsDB = ListsDataPostgres()
    override val cards: CardsDB = CardsDataPostgres()
}