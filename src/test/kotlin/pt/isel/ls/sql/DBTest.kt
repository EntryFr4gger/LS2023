package pt.isel.ls.sql
import org.postgresql.ds.PGSimpleDataSource
import kotlin.test.Test
import kotlin.test.assertEquals

class SqlTests {
    companion object {
        private val dataSource = PGSimpleDataSource().also{it.setURL(System.getenv("JDBC_DATABASE_URL"))}
    }

    @Test
    fun testConnection() {

        dataSource.connection.use {connect ->
            val stm = connect.prepareStatement("insert into courses(name) values ('LEIM');").executeQuery().also { it.next() }
            assertEquals("Alice", stm.getString("name"))
        }
    }


    @Test
    fun insert() {
        dataSource.connection.use {connect ->
            val stm = connect.prepareStatement("insert into courses(name) values ('LEIM');").executeQuery().also { it.next() }
            assertEquals("Alice", stm.getString("name"))
        }
    }

    @Test
    fun update() {
        dataSource.connection.use {connect ->
            val stm = connect.prepareStatement("select * from students").executeQuery().also { it.next() }
            assertEquals("Alice", stm.getString("name"))
        }
    }

    @Test
    fun select() {
        dataSource.connection.use {connect ->
            val stm = connect.prepareStatement("select * from students").executeQuery().also { it.next() }
            assertEquals("Alice", stm.getString("name"))
        }
    }

    @Test
    fun delete() {
        dataSource.connection.use {connect ->
            val stm = connect.prepareStatement("select * from students").executeQuery().also { it.next() }
            assertEquals("Alice", stm.getString("name"))
        }
    }
}