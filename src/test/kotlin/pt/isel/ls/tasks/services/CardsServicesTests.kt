package pt.isel.ls.tasks.services

import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.domain.Card
import pt.isel.ls.tasks.services.errors.ServicesError
import kotlin.test.Ignore
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class CardsServicesTests : ClearData() {
    private val storage = TasksDataStorage()
    private val source = TasksDataMem(storage)
    private val services = TaskServices(source)

    @Test
    fun `Create new card correctly`() {
        source.run {
            val cardId = services.cards.createNewCard("card", "card", null, 1, 1, 1)
            assertEquals(
                Card(cardId, "card", "card", null, 1, 1, 1),
                storage.cards[cardId]
            )
        }
    }

    @Test
    fun `Create new card throws InvalidArgumentException if name is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.cards.createNewCard("B", "asdsa", null, 1, null, 1)
        }
    }

    @Test
    fun `Create new card throws InvalidArgumentException if description is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.cards.createNewCard("Cards", "", null, 1, null, 1)
        }
    }

    @Test
    fun `Create new card throws InvalidArgumentException if board id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.cards.createNewCard("Cards", "asdsa", null, -2, null, 1)
        }
    }

    @Test
    fun `Create new card throws InvalidArgumentException if list id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.cards.createNewCard("Cards", "asdsa", null, 1, -2, 1)
        }
    }

    @Test
    fun `Create new card throws AuthorizationException if user don't have permission`() {
        assertFailsWith<ServicesError.AuthorizationException> {
            services.cards.createNewCard("Cards", "asdsa", null, 1, null, 3)
        }
    }

    @Test
    fun `Get card details correctly`() {
        source.run {
            assertEquals(
                storage.cards[2],
                services.cards.getCardDetails(2, 1)
            )
        }
    }

    @Test
    fun `Get card details throws InvalidArgumentException if card id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.cards.getCardDetails(-2, 1)
        }
    }

    @Test
    fun `Get card details throws AuthorizationException if user don't have permission`() {
        assertFailsWith<ServicesError.AuthorizationException> {
            services.cards.getCardDetails(1, 3)
        }
    }

    @Test
    fun `move card correctly`() {
        assertTrue { services.cards.moveCard( 3, 2,1) }
    }

    @Test
    fun `move card throws InvalidArgumentException if list id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.cards.moveCard(-2, 1, 1)
        }
    }

    @Test
    fun `move card throws InvalidArgumentException if card id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.cards.moveCard(1, -1, 1)
        }
    }

    @Test
    fun `Delete card throws InvalidArgumentException if board id is wrong`() {
        assertFailsWith<ServicesError.InvalidArgumentException> {
            services.cards.deleteCard(-1, Int.MAX_VALUE)
        }
    }

    @Test
    fun `Delete card throws AuthorizationException if user don't have permission`() {
        assertFailsWith<ServicesError.AuthorizationException> {
            services.cards.deleteCard(1, 3)
        }
    }
}
