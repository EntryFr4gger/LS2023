package pt.isel.ls.tasks.db

import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.modules.users.UsersDataMem
import pt.isel.ls.tasks.db.modules.users.UsersDataPostgres
import java.sql.Connection


class TasksDataMem(storage: TasksDataStorage): TaskData{
    override fun  execute(): Connection? = null


    override val users = UsersDataMem(storage)
    override val boards = TODO()
    override val lists = TODO()
    override val cards = TODO()
}