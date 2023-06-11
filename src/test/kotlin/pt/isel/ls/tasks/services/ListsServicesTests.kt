package pt.isel.ls.tasks.services

import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.domain.Card
import pt.isel.ls.tasks.domain.List
import pt.isel.ls.tasks.services.errors.ServicesError
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ListsServicesTests : ClearData() {
    private val storage = TasksDataStorage()
    private val source = TasksDataMem(storage)
    private val services = TaskServices(source)

    @Test
    fun `create list correctly`() {
        source.run {
            val userId = source.users.createNewUser(it, "Armandio", "Armandio@gmail.com", "Adsfs123&")
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
            services.lists.createList("list", 1, 3)
        }
    }

    @Test
    fun `get list details correctly`() {
        source.run {
            val userId = source.users.createNewUser(it, "Armandio", "Armandio@gmail.com", "Adsfs123&")
            val boardId = source.boards.createNewBoard(it, "Armandio", "sadsad")
            source.boards.addUserToBoard(it, userId, boardId)
            val listId = source.lists.createList(it, "list", boardId)
            assertEquals(
                List(listId, "list", boardId),
                services.lists.getListDetails(listId, userId, emptyList()).list
            )
        }
    }

    @Test
    fun `get list details throws InvalidArgumentException if list id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.lists.getListDetails(-2, 1, emptyList())
        }
    }

    @Test
    fun `get list details throws AuthorizationException if user don't have permission`() {
        assertFailsWith<ServicesError.AuthorizationException> {
            services.lists.getListDetails(1, 3, emptyList())
        }
    }

    @Test
    fun `get cards of list correctly`() {
        source.run {
            val userId = source.users.createNewUser(it, "Armandio", "Armandio@gmail.com", "Adsfs123&")
            val boardId = source.boards.createNewBoard(it, "Armandio", "sadsad")
            val listId = source.lists.createList(it, "list", boardId)
            source.boards.addUserToBoard(it, userId, boardId)
            val cardId = services.cards.createNewCard("card", "card", null, boardId, listId, userId)
            assertEquals(
                listOf(Card(cardId, "card", "card", null, 1, boardId, listId)),
                services.lists.getCardsOfList(listId, 0, 10, userId)
            )
        }
    }

    @Test
    fun `get cards of list throws InvalidArgumentException if board id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.lists.getCardsOfList(-2, 1, 1, 1)
        }
    }

    @Test
    fun `get cards of list throws AuthorizationException if user don't have permission`() {
        assertFailsWith<ServicesError.AuthorizationException> {
            services.lists.getCardsOfList(1, 1, 1, 3)
        }
    }

    @Test
    fun `Repositions a card correctly`() {
        source.run {
            val userId = source.users.createNewUser(it, "Armandio", "Armandio@gmail.com", "Adsfs123&")
            val boardId = source.boards.createNewBoard(it, "Armandio", "sadsad")
            val listId = source.lists.createList(it, "list", boardId)
            val cards = mutableListOf<Int>()
            for (i in 0..5){
                cards.add(source.cards.createNewCard(it,"card$i","",null,boardId,listId))
            }
            source.boards.addUserToBoard(it, userId, boardId)

            //val cardId = services.lists.respositionCard(listId, "card", null, boardId, listId, userId)

            assertEquals(
                listOf(Card(1, "card", "card", null, 1, boardId, listId)),
                services.lists.getCardsOfList(listId, 0, 10, userId)
            )
        }
    }
}
