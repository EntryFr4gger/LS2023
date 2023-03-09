package pt.isel.ls.sql
import org.postgresql.ds.PGSimpleDataSource
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class SqlTests {
    companion object {
        private val dataSource = PGSimpleDataSource().also{it.setURL(System.getenv("JDBC_DATABASE_URL"))}
    }

    @Test
    fun testConnection() {
        assertNotNull(dataSource.getConnection())
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
                val rs = connect.prepareStatement("select * from courses where name='test'").executeQuery().also { it.next() }
                assertNotNull(rs)
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
                val rs = connect.prepareStatement("select * from courses where name='LEIM'").executeQuery().also { it.next() }
                assertNotNull(rs)
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
                assertEquals(rs.next(),true)
                connect.prepareStatement("delete from courses where name = 'test';").executeUpdate()
                val deleteRs = connect.prepareStatement("select * from courses where name='test'").executeQuery()
                assertEquals(deleteRs.next(),false)
            } finally {
                connect.rollback()
            }
        }
    }
}