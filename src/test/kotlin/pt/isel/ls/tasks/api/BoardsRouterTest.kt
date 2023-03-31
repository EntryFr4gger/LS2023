package pt.isel.ls.tasks.api

import org.http4k.core.RequestContexts
import pt.isel.ls.tasks.api.routers.boards.BoardsRouter
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.services.modules.boards.BoardsServices

class BoardsRouterTest {
    private val storage = TasksDataStorage()
    private val source = TasksDataMem(storage)
    private val boardsServices = BoardsServices(source)
    val context = RequestContexts()
    val boardsRouter = BoardsRouter(boardsServices, context)
}
