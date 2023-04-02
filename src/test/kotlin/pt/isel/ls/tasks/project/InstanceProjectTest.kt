package pt.isel.ls.tasks.project

import org.http4k.client.JavaHttpClient
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.slf4j.LoggerFactory
import pt.isel.ls.tasks.PORT
import pt.isel.ls.tasks.api.TasksAPI
import pt.isel.ls.tasks.db.TasksDataMem
import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.services.TaskServices

abstract class InstanceProjectTest {
    companion object {
        const val path = "http://localhost:$PORT/"
        val send = JavaHttpClient()
        val logger = LoggerFactory.getLogger("Tasks API")
        val db = TasksDataMem(TasksDataStorage())
        val services =
            TaskServices(db) // TaskServices(TasksDataPostgres("JDBC_DATABASE_URL"))
        val app = TasksAPI.invoke(services, logger)

        val jettyServer = app.asServer(Jetty(PORT)).start()
    }
}
