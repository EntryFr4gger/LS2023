package pt.isel.ls.sql
import kotlin.test.Test
import kotlin.test.assertEquals

class SqlTests {
    @Test
    fun example() {
        // arrange | given
        val a = 1
        val b = 2

        // act | when
        val result = a + b

        // assert | then
        assertEquals(3, result)
    }
}