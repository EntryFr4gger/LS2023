package pt.isel.ls.tasks.services

import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.modules.users.UsersDataMem
import pt.isel.ls.tasks.domain.User

class Services {
    fun getUser(id : Int): User? {
        val storage = TasksDataStorage()
        return storage.users[id]
        UsersDataMem().getUserDetails(id)
    }
}
