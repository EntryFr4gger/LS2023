package pt.isel.ls.tasks.services

import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.domain.Board
import pt.isel.ls.tasks.domain.Card
import pt.isel.ls.tasks.domain.List
import pt.isel.ls.tasks.services.errors.ServicesError
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class ListsServicesTests : ClearData() {
    private val storage = TasksDataStorage()
    private val source = TasksDataMem(storage)
    private val services = TaskServices(source)

    @Test
    fun `create list correctly`() {
        source.run {
            val userId = source.users.createNewUser(it, "Armandio", "Armandio@gmail.com")
            val boardId = source.boards.createNewBoard(it, "Armandio", "sadsad")
            source.boards.addUserToBoard(it, userId, boardId)
            val listId = services.lists.createList("list", boardId, userId)
            assertEquals(List(listId, "list", boardId), source.lists.getListDetails(it, listId))
        }
    }


    @Test
    fun `create list throws InvalidArgumentException if name is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.lists.createList("B", 1, 1)
        }
    }

    @Test
    fun `create list throws InvalidArgumentException if id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.lists.createList("list", -1, 1)
        }
    }

    @Test
    fun `create list throws AuthorizationException if user don't have permission`() {
        assertFailsWith<ServicesError.AuthorizationException> {
            services.lists.createList("list", 1, -2)
        }
    }

    @Test
    fun `get list details correctly`() {
        source.run {
            val userId = source.users.createNewUser(it, "Armandio", "Armandio@gmail.com")
            val boardId = source.boards.createNewBoard(it, "Armandio", "sadsad")
            source.boards.addUserToBoard(it, userId, boardId)
            val listId = source.lists.createList(it, "list", boardId)
            assertEquals(List(listId, "list", boardId), services.lists.getListDetails(listId, userId))

        }
    }

    @Test
    fun `get list details throws InvalidArgumentException if list id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.lists.getListDetails(-2, 1)
        }
    }

    @Test
    fun `get list details throws AuthorizationException if user don't have permission`() {
        assertFailsWith<ServicesError.AuthorizationException> {
            services.lists.getListDetails(1, -2)
        }
    }


    @Test
    fun `get cards of list correctly`() {
        source.run {
            val userId = source.users.createNewUser(it, "Armandio", "Armandio@gmail.com")
            val boardId = source.boards.createNewBoard(it, "Armandio", "sadsad")
            val listId = source.lists.createList(it, "list", boardId)
            source.boards.addUserToBoard(it, userId, boardId)
            val cardId = source.cards.createNewCard(it, "card", "card", null, boardId, listId)
            assertEquals(
                listOf(Card(cardId, "card", "card", null, boardId, listId)),
                services.lists.getCardsOfList(listId, userId)
            )
        }
    }

    @Test
    fun `get cards of list throws InvalidArgumentException if board id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.lists.getCardsOfList(-2, 1)
        }
    }

    @Test
    fun `get cards of list throws AuthorizationException if user don't have permission`() {
        assertFailsWith<ServicesError.AuthorizationException> {
            services.lists.getCardsOfList(1, -2)
        }
    }
}