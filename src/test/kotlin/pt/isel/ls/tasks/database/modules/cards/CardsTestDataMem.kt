package pt.isel.ls.tasks.database.modules.cards

import org.junit.jupiter.api.Test
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.modules.lists.ListsDataMem
import pt.isel.ls.tasks.model.*

class CardsTestDataMem: CardsTestDB {
    private val storage = TasksDataStorage()
    private val source = ListsDataMem(storage)


}