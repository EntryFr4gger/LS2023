package pt.isel.ls.tasks.database.modules.cards

import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.modules.lists.ListsDataMem

class CardsTestDataMem: CardsTestDB {
    private val storage = TasksDataStorage()
    private val source = ListsDataMem(storage)


}