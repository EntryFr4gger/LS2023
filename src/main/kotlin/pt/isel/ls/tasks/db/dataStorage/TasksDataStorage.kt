package pt.isel.ls.tasks.db.dataStorage

import pt.isel.ls.tasks.domain.*
import pt.isel.ls.tasks.domain.List
import java.util.concurrent.atomic.AtomicInteger

class TasksDataStorage {
    val users = HashMap<Int, User>()
    var nextUserId = AtomicInteger(1)

    val boards = HashMap<Int, Board>()
    var nextBoardId = AtomicInteger(1)

    val lists = HashMap<Int, List>()
    var nextListId = AtomicInteger(1)

    val cards = HashMap<Int, Card>()
    var nextCardId = AtomicInteger(1)

    // Int -> User ID  List<Int> -> List of Board ID's
    val userBoard = HashMap<Int, kotlin.collections.List<Int>>()

}
