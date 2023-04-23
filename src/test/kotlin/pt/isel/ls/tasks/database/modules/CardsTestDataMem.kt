package pt.isel.ls.tasks.database.modules

import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.errors.NotFoundException
import pt.isel.ls.tasks.db.modules.cards.CardsDataMem
import pt.isel.ls.tasks.domain.Card
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CardsTestDataMem {
    private val storage = TasksDataStorage()
    private val source = TasksDataMem(storage)
    private val cards = CardsDataMem(storage)

    @Test
    fun `Creates a new card in a list`() {
        source.run { conn ->
            val card = Card(
                1,
                "Study",
                "study for success",
                LocalDate(2023, 3, 21),
                null,
                1,
                1
            )
            val id =
                this.cards.createNewCard(conn, card.name, card.description, card.dueDate, card.boardId, card.listId)
            val cardCreated = card.copy(id = id, cix = 2)
            assertEquals(storage.cards[id],cardCreated)
        }
    }

    @Test
    fun `Get detail in a card`() {
        source.run { conn ->
            val card = Card(2, "Entrega 1", "Entrega inicial do autorouter", LocalDate(2023, 4, 3), 1,1, 2)
            val res = cards.getCardDetails(conn, 2)
            assertEquals(card, res)
        }
    }

    @Test
    fun `move card from a list`() {
        source.run { conn ->
            val res = cards.moveCard(conn, 3, 2)
            assertTrue { res }
        }
    }

    @Test
    fun `Throws an error for a nonexistent card`() {
        source.run { conn ->
            assertFailsWith<NotFoundException> {
                cards.getCardDetails(conn, 10)
            }
        }
    }

    @Test
    fun `Confirm that the card already exist`() {
        source.run { conn ->
            assertTrue { cards.hasCard(conn, 1) }
        }
    }

    @Test
    fun `Confirm that the card do not exist`() {
        source.run { conn ->
            assertFalse { cards.hasCard(conn, 69) }
        }
    }

    @Test
    fun `delete card from a list`(){
        source.run { conn ->
            cards.deleteCard(conn, 1)
            assertFalse {  cards.hasCard(conn,1)}
        }
    }
}
