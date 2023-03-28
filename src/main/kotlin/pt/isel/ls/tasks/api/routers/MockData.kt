package pt.isel.ls.tasks.api.routers

import pt.isel.ls.tasks.api.routers.boards.lists.models.ListInfo
import pt.isel.ls.tasks.api.routers.boards.models.Board
import pt.isel.ls.tasks.api.routers.users.models.ReturnGetUser
import pt.isel.ls.tasks.domain.Card

class MockData {
    companion object{
        val Users = mutableListOf(
            ReturnGetUser( 10,"Filipe","Filipe@gmail.com"),
            ReturnGetUser( 10,"Duarte","Duarte@gmail.com"),
            ReturnGetUser( 10,"Afonso","Afonso@gmail.com")
        )
        val boards = mutableListOf(
            Board( 1,"LS","Board de LS", emptyList()),
            Board( 2,"PC","Board de PC",emptyList()),
            Board( 3,"LAE","Board de LAE",emptyList())
        )
        val userBoards = mutableListOf(
            Board( 1,"LS","Board de LS",emptyList()),
            Board( 3,"LAE","Board de LAE",emptyList())
        )

        val lists = mutableListOf(
            ListInfo(1,"Trabalho1", emptyList()),
            ListInfo(2,"Trabalho2", emptyList())
        )
    }
}
