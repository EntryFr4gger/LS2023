package pt.isel.ls.tasks.database.modules.cards

import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.modules.cards.CardsDataMem
import pt.isel.ls.tasks.domain.*
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CardsTestDataMem {
    private val storage = TasksDataStorage()
    private val source = TasksDataMem(storage)
    private val cards = CardsDataMem(storage)
    @Test
     fun `Creates a new card in a list` (){
        source.run {conn ->
            val card = Card(1,"Study","study for success",
                LocalDate(2023,3,21),1,1 )
            val id = this.cards.createNewCard(conn,card.name,card.description,card.dueDate,card.boardId,card.listId)
            val cardCreated = card.copy(id= id)
            assertEquals(cardCreated, storage.cards[id])
        }
    }

    @Test
    fun `get all cards in the same list` (){
        source.run { conn->
            val cards = listOf(
                Card(3, "Ração", "Ração daquela que os cães comem e tal", LocalDate(2023,3,21), 2, 3),
                      Card(4, "Trela nova", "Daquela para eles n andarem muito para a frente", LocalDate(2023,3,21), 2, 3)
            )

            val res = this.cards.getCardsOfList(conn, 3)
            assertEquals(cards, res)
        }
    }

    @Test
    fun`Get detail in a card`(){
        source.run { conn ->
            val card =  Card(2, "Entrega 1", "Entrega inicial do autorouter", LocalDate(2023,4,3), 1, 2)
            val res = cards.getCardDetails(conn, 2)
            assertEquals(card, res)
        }
    }

    @Test
    fun`move card from a list`() {
        source.run { conn ->
            val res = cards.moveCard(conn, 3, 2)
            assertEquals(1,res )
        }
    }

    @Test
    fun `Throws an error for a nonexistent card`(){
        source.run { conn ->
            assertFailsWith<IllegalStateException> {
               cards.getCardDetails(conn,10)
            }
        }
    }

    @Test
    fun `Confirm that the card already exist`(){
        source.run {conn ->
            assertTrue { cards.hasCard(conn, 1) }
        }
    }

    @Test
    fun `Confirm that the card do not exist`(){
        source.run {conn ->
            assertFalse { cards.hasCard(conn, 69) }
        }
    }
}