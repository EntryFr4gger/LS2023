package pt.isel.ls.tasks.api.routers

import pt.isel.ls.tasks.api.routers.boards.models.Board
import pt.isel.ls.tasks.api.routers.users.models.ReturnGetUser

class MockData {
    companion object{
        val Users = mutableListOf(
            ReturnGetUser( 10,"Filipe","Filipe@gmail.com"),
            ReturnGetUser( 10,"Duarte","Duarte@gmail.com"),
            ReturnGetUser( 10,"Afonso","Afonso@gmail.com")
        )
        val boards = mutableListOf(
            Board( 1,"LS","Board de LS"),
            Board( 2,"PC","Board de PC"),
            Board( 3,"LAE","Board de LAE")
        )
        val userBoards = mutableListOf(
            Board( 1,"LS","Board de LS"),
            Board( 3,"LAE","Board de LAE")
        )
    }
}
