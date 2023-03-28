package pt.isel.ls.tasks.db

import pt.isel.ls.tasks.db.modules.cards.CardsDataPostgres

fun main() {
    val source = TasksDataPostgres("JDBC_DATABASE_URL")
    val sc = CardsDataPostgres()
    var key = 1
    source.execute {
        key = sc.createNewCard(
            it,
            "${getRandomString(6)}",
            "cenas",
            null,
            1,
            null
        )
        println(key)
    }
    source.execute {
        val res = sc.getCardDetails(it, key)
        println(res)
    }
    source.execute {
        val res = sc.getCardsOfList(it, 1)
        println(res)
    }
    source.execute {
        val res = sc.moveCard(it, key, 1)
        println(res)
    }
    source.execute {
        val res = sc.getCardsOfList(it, 1)
        println(res)
    }
}

fun getRandomString(length: Int): String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
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

/*
val sc = BoardsDataMem(TasksDataStorage())
val sc1 = UsersDataMem(TasksDataStorage())
sc.createNewBoard(null, "Bernardo", "Boardsa")
sc.createNewBoard(null, "Bsdaernardo", "boarsda")

sc1.createNewUser(null, "Bernardo", "Email@sadasd.pt")

sc.addUserToBoard(null,1, 1)
sc.addUserToBoard(null,1, 2)
println(sc.getUserBoards(null, 1))
println(sc.getBoardDetails(null, 1))*/

/*
val sc = UsersDataPostgres()
var key = 1
source.execute{
    key = sc.createNewUser(it, "Bernardo", "${getRandomString(6)}@isel.pt")
    println(key)
}
source.execute {
    val res = sc.getUserDetails(it, key)
    println(res)
}*/

/*
val source = TasksDataPostgres("JDBC_DATABASE_URL")
val sc = BoardsDataPostgres()
var key = 1
source.execute{
    key = sc.createNewBoard(it, "${getRandomString(6)}", "dsadsaa")
    println(key)
}
source.execute {
    val res = sc.getBoardDetails(it, key)
    println(res)
}
source.execute {
    val res = sc.addUserToBoard(it, 1, key)
    println(res)
}
source.execute {
    val res = sc.getUserBoards(it, 1)
    println(res)
}*/

/*
val source = TasksDataPostgres("JDBC_DATABASE_URL")
    val sc = CardsDataPostgres()
    var key = 1
    source.execute{
            key = sc.createNewCard(it,
                "${getRandomString(6)}",
                "cenas",
                LocalDate(1,2,2),
                1,
                null)
        println(key)
    }
    source.execute {
        val res = sc.getCardDetails(it, key,1)
        println(res)
    }
    source.execute {
        val res = sc.getCardsOfList(it, 1)
        println(res)
    }
    source.execute {
        val res = sc.moveCard(it, key,1)
        println(res)
    }
    source.execute {
        val res = sc.getCardsOfList(it, 1)
        println(res)
    }
    */
