package pt.isel.ls.tasks.services

import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.domain.List
import pt.isel.ls.tasks.services.errors.ServicesError
import kotlin.test.Ignore
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class ListsServicesTests : ClearData() {
    private val storage = TasksDataStorage()
    private val source = TasksDataMem(storage)
    private val services = TaskServices(source)

    @Test
    fun `Create list correctly`() {
        val listId = services.lists.createList("Study", 1, 1)
        assertEquals(
            List(listId, "Study", 1),
            storage.lists[listId]
        )
    }

    @Test
    fun `Create list throws InvalidArgumentException if name is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.lists.createList("B", 1, 1)
        }
    }

    @Test
    fun `Create list throws InvalidArgumentException if id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.lists.createList("list", -1, 1)
        }
    }

    @Test
    fun `Create list throws AuthorizationException if user don't have permission`() {
        assertFailsWith<ServicesError.AuthorizationException> {
            services.lists.createList("list", 1, 3)
        }
    }

    @Test
    @Ignore
    fun `Move card in list correctly`() {
        services.lists.respositionCard(1, 1, 3, 1)
        assertTrue(storage.cards[1]!!.cix == 2)
    }

    @Test
    fun `Move card in list throws InvalidArgumentException if name is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.lists.respositionCard(-1, 1, 3, 1)
        }
    }

    @Test
    fun `Move card in list throws InvalidArgumentException if id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.lists.respositionCard(1, -1, 3, 1)
        }
    }

    @Test
    fun `Move card in list throws AuthorizationException if user don't have permission to the card`() {
        assertFailsWith<ServicesError.AuthorizationException> {
            services.lists.respositionCard(3, 4, 3, 3)
        }
    }

    @Test
    fun `Move card in list throws AuthorizationException if user don't have permission to the list`() {
        assertFailsWith<ServicesError.AuthorizationException> {
            services.lists.respositionCard(2, 1, 3, 3)
        }
    }

    @Test
    fun `Get list details correctly`() {
        source.run {
            assertEquals(
                storage.lists[1],
                services.lists.getListDetails(1, 1, emptyList()).list
            )
        }
    }

    @Test
    fun `Get list details throws InvalidArgumentException if list id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.lists.getListDetails(-2, 1, emptyList())
        }
    }

    @Test
    fun `Get list details throws AuthorizationException if user don't have permission`() {
        assertFailsWith<ServicesError.AuthorizationException> {
            services.lists.getListDetails(1, 3, emptyList())
        }
    }

    @Test
    fun `Get cards of list correctly`() {
        assertEquals(
            listOf(storage.cards[4]),
            services.lists.getCardsOfList(3, 1, 1, 1)
        )
    }

    @Test
    fun `Get cards of list throws InvalidArgumentException if board id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.lists.getCardsOfList(-2, 1, 1, 1)
        }
    }

    @Test
    fun `Get cards of list throws AuthorizationException if user don't have permission`() {
        assertFailsWith<ServicesError.AuthorizationException> {
            services.lists.getCardsOfList(1, 1, 1, 3)
        }
    }

    @Test
    fun `Delete list throws InvalidArgumentException if board id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.lists.deleteList(-1, Int.MAX_VALUE)
        }
    }

    @Test
    fun `Delete list throws AuthorizationException if user don't have permission`() {
        assertFailsWith<ServicesError.AuthorizationException> {
            services.lists.deleteList(1, 3)
        }
    }
}
