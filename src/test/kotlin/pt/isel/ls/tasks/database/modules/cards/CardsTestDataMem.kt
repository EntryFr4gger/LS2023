package pt.isel.ls.tasks.database.modules.cards

import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.modules.cards.CardsDataMem
import pt.isel.ls.tasks.domain.*
import kotlin.test.assertEquals

class CardsTestDataMem : CardsTestDB {
    private val storage = TasksDataStorage()
    private val source = TasksDataMem(storage)
    private val card = CardsDataMem(storage)

    @Test
    fun `Creates a new card in a list`() {
        source.execute {
            val card = Card(
                1,
                "Study",
                "study for success",
                LocalDate(2023, 3, 21),
                1,
                1
            )
            val id = this.card.createNewCard(it, card.name, card.description, card.dueDate, card.boardId, card.listId)
            assertEquals(id, card.id)
            assertEquals(card, storage.cards[id])
        }
    }

    @Test
    fun `get all cards in the same list`() {
        source.execute { conn ->
            val cards = listOf(
                Card(
                    1,
                    "Study",
                    "study for success",
                    LocalDate(2023, 3, 21),
                    1,
                    1
                ),
                Card(
                    2,
                    "Work",
                    "work for success",
                    LocalDate(2023, 3, 21),
                    1,
                    1
                )
            )
            val ids = cards.map {
                card.createNewCard(
                    conn,
                    it.name,
                    it.description,
                    it.dueDate,
                    it.boardId,
                    it.listId
                )
            }
            assertEquals(ids, listOf(1, 2))
            assertEquals(cards, card.getCardsOfList(conn, 1))
        }
    }

    @Test
    fun`Get detail in a card`() {
        source.execute { conn ->
            val cards = listOf(
                Card(
                    1,
                    "Study",
                    "study for success",
                    LocalDate(2023, 3, 21),
                    1,
                    1
                ),
                Card(
                    2,
                    "Work",
                    "work for success",
                    LocalDate(2023, 3, 21),
                    1,
                    1
                )
            )
            val ids = cards.map {
                card.createNewCard(
                    conn,
                    it.name,
                    it.description,
                    it.dueDate,
                    it.boardId,
                    it.listId
                )
            }
            assertEquals(cards[0], card.getCardDetails(conn, 1))
        }
    }

    @Test
    fun`move card from a list`() {
        source.execute { conn ->
            val card = Card(
                1,
                "Study",
                "study for success",
                LocalDate(2023, 3, 21),
                1,
                1
            )
            val id = this.card.createNewCard(
                conn,
                card.name,
                card.description,
                card.dueDate,
                card.boardId,
                card.listId
            )
            val res = this.card.moveCard(conn, 1, 2)
            assertEquals(1, res)
            // assertEquals(newCard, storage.cards[id])
        }
    }
}
