package pt.isel.ls.tasks.database.modules.cards

import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.modules.*
import pt.isel.ls.tasks.db.modules.cards.CardsDataMem
import pt.isel.ls.tasks.model.*
import kotlin.test.assertEquals

class CardsTestDataMem: CardsTestDB {
    private val storage = TasksDataStorage()
    private val source = CardsDataMem(storage)
    @Test
     fun `Creates a new card in a list` (){
        val card = Card(1,"Study","study for success",
            LocalDate(2023,3,21),1,1 )
        val id = source.createNewCard(null,card.name,card.description,card.dueDate,card.boardId,card.listId)
        assertEquals(id, card.id)
        assertEquals(card, storage.cards[id])
    }

    @Test
    fun `get all cards in the same list` (){
        val cards = listOf (Card(1,"Study","study for success",
            LocalDate(2023,3,21),1,1 ),
            Card(2,"Work","work for success",
            LocalDate(2023,3,21),1,1 ))
        val ids = cards.map { source.createNewCard(null,it.name,it.description,it.dueDate,it.boardId,it.listId) }
        assertEquals(ids, listOf(1,2) )
        assertEquals(cards,source.getAllCards(null,1))
    }

    @Test
    fun`Get detail in a card`(){
        val cards = listOf (Card(1,"Study","study for success",
            LocalDate(2023,3,21),1,1 ),
            Card(2,"Work","work for success",
                LocalDate(2023,3,21),1,1 ))
        val ids = cards.map { source.createNewCard(null,it.name,it.description,it.dueDate,it.boardId,it.listId) }
        assertEquals(cards[0], source.getCardDetails(null,1,1))
    }

    @Test
    fun`move card from a list`(){
        val card = Card(1,"Study","study for success",
            LocalDate(2023,3,21),1,1 )
        val id = source.createNewCard(null,card.name,card.description,card.dueDate,card.boardId,card.listId)
        val newCard = source.moveCard(null,1,2)
        assertEquals(2,newCard.listId )
        assertEquals(newCard, storage.cards[id])
    }

}