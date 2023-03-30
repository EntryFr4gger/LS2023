package pt.isel.ls.tasks.database.modules.cards

import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.modules.cards.CardsDataMem
import pt.isel.ls.tasks.domain.*
import kotlin.test.assertEquals

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

            assertEquals(cards, this.cards.getCardsOfList(conn, 3))
        }
    }

    @Test
    fun`Get detail in a card`(){
        source.run { conn ->
            val card =  Card(2, "Entrega 1", "Entrega inicial do autorouter", LocalDate(2023,4,3), 1, 2)
            assertEquals(card, cards.getCardDetails(conn, 2))
        }
    }

    @Test
    fun`move card from a list`() {
        source.run { conn ->

            val res = cards.moveCard(conn, 3, 2)
            assertEquals(1,res )
        }
    }
}