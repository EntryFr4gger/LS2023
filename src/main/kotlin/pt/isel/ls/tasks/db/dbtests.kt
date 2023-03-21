package pt.isel.ls.tasks.db

import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.modules.users.UsersDataMem


fun main(){
    val sc = UsersDataMem(TasksDataStorage())
    sc.createNewUser(null, "Bernardo", "Email@sadasd.pt")
    sc.createNewUser(null, "Bsdaernardo", "sadasdsa@sadasd.pt")
    println(sc.getUserDetails(null, 1))
    println(sc.getUserDetails(null, 2))
}






/*    val source = TasksDataPostgres(System.getenv("JDBC_DATABASE_URL"))
    source.execute().use {
        println("ALI")
        val stm = it!!.prepareStatement("select * from users")
        val rs = stm.executeQuery()
        while (rs.next()) {
            println(rs.getString("name"))
        }
    }*/
