package pt.isel.ls.sql
import org.postgresql.ds.PGSimpleDataSource
import java.net.ConnectException
import kotlin.test.*

class SqlTests {
    companion object {
        private val dataSource = PGSimpleDataSource().also{it.setURL(System.getenv("JDBC_DATABASE_URL"))}
    }

    @Test
    fun testConnection() {
      try{
          dataSource.connection.use {  }
      }
      catch (_: ConnectException){}
    }

    @Test
    fun select() {
        dataSource.connection.use {connect ->
            val stm = connect.prepareStatement("select * from students").executeQuery().also { it.next() }
            assertEquals("Alice", stm.getString("name"))
        }
    }

    @Test
    fun insert() {
        dataSource.connection.use {connect ->
            try {
                connect.autoCommit = false
                connect.prepareStatement("insert into courses(name) values ('test');").executeUpdate()
                val rs = connect.prepareStatement("select * from courses where name='test'").executeQuery()
                assertTrue(rs.next())
                assertEquals("test", rs.getString("name"))
            } finally {
                connect.rollback()
            }
        }
    }

    @Test
    fun update() {
        dataSource.connection.use {connect ->
            try {
                connect.autoCommit = false
                connect.prepareStatement("update courses set name = 'LEIM' where name = 'LEIC';").executeUpdate()
                val rs = connect.prepareStatement("select * from courses where name='LEIM'").executeQuery()
                assertTrue(rs.next())
                assertEquals("LEIM", rs.getString("name"))
            } finally {
                connect.rollback()
            }
        }
    }

    @Test
    fun delete() {
        dataSource.connection.use {connect ->
            try {
                connect.autoCommit = false
                connect.prepareStatement("insert into courses(name) values ('test');").executeUpdate()
                val rs = connect.prepareStatement("select * from courses where name='test'").executeQuery()
                assertTrue(rs.next())
                connect.prepareStatement("delete from courses where name = 'test';").executeUpdate()
                val deleteRs = connect.prepareStatement("select * from courses where name='test'").executeQuery()
                assertFalse(deleteRs.next())
            } finally {
                connect.rollback()
            }
        }
    }
}