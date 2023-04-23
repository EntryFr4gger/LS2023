package pt.isel.ls.tasks.services

import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.domain.Card
import pt.isel.ls.tasks.services.errors.ServicesError
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class CardsServicesTests : ClearData() {
    private val storage = TasksDataStorage()
    private val source = TasksDataMem(storage)
    private val services = TaskServices(source)

    @Test
    fun `create new card correctly`() {
        source.run {
            val userId = source.users.createNewUser(it, "Armandio", "Armandio@gmail.com")
            val boardId = source.boards.createNewBoard(it, "Armandio", "sadsad")
            source.boards.addUserToBoard(it, userId, boardId)
            val cardId = services.cards.createNewCard("card", "card", null, boardId, null, userId)
            assertEquals(
                Card(cardId, "card", "card", null, null, boardId, null),
                source.cards.getCardDetails(it, cardId)
            )
        }
    }

    @Test
    fun `create new card throws InvalidArgumentException if name is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.cards.createNewCard("B", "asdsa", null, 1, null, 1)
        }
    }

    @Test
    fun `create new card throws InvalidArgumentException if description is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.cards.createNewCard("Cards", "", null, 1, null, 1)
        }
    }

    @Test
    fun `create new card throws InvalidArgumentException if board id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.cards.createNewCard("Cards", "asdsa", null, -2, null, 1)
        }
    }

    @Test
    fun `create new card throws InvalidArgumentException if list id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.cards.createNewCard("Cards", "asdsa", null, 1, -2, 1)
        }
    }

    @Test
    fun `create new card throws AuthorizationException if user don't have permission`() {
        assertFailsWith<ServicesError.AuthorizationException> {
            services.cards.createNewCard("Cards", "asdsa", null, 1, null, -2)
        }
    }

    @Test
    fun `get card details correctly`() {
        source.run {
            val userId = source.users.createNewUser(it, "Armandio", "Armandio@gmail.com")
            val boardId = source.boards.createNewBoard(it, "Armandio", "sadsad")
            source.boards.addUserToBoard(it, userId, boardId)
            val cardId = source.cards.createNewCard(it, "card", "card", null, boardId, null)
            assertEquals(
                Card(cardId, "card", "card", null, null, boardId, null),
                services.cards.getCardDetails(cardId, userId)
            )
        }
    }

    @Test
    fun `get card details throws InvalidArgumentException if card id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.cards.getCardDetails(-2, 1)
        }
    }

    @Test
    fun `get card details throws AuthorizationException if user don't have permission`() {
        assertFailsWith<ServicesError.AuthorizationException> {
            services.cards.getCardDetails(1, -2)
        }
    }

    @Test
    fun `move card correctly`() {
        source.run {
            val userId = source.users.createNewUser(it, "Armandio", "Armandio@gmail.com")
            val boardId = source.boards.createNewBoard(it, "Armandio", "sadsad")
            val listId = source.lists.createList(it, "list", boardId)
            source.boards.addUserToBoard(it, userId, boardId)
            val cardId = source.cards.createNewCard(it, "card", "card", null, boardId, null)
            assertTrue(services.cards.moveCard(listId, cardId, 1, userId))
            assertTrue(
                source.lists.getCardsOfList(it, 1, 1, listId)
                    .contains(Card(cardId, "card", "card", null, 1, boardId, listId))
            )
        }
    }

    @Test
    fun `move card throws InvalidArgumentException if list id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.cards.moveCard(-2, 1, 1, 1)
        }
    }

    @Test
    fun `move card throws InvalidArgumentException if card id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.cards.moveCard(1, -1, 1, 1)
        }
    }
}
