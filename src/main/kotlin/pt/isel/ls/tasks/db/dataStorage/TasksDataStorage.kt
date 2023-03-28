package pt.isel.ls.tasks.db.dataStorage

import pt.isel.ls.tasks.domain.*
import pt.isel.ls.tasks.domain.List
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

class TasksDataStorage {
    val users = ConcurrentHashMap<Int, User>()
    var nextUserId = AtomicInteger(1)

    val boards = ConcurrentHashMap<Int, Board>()
    var nextBoardId = AtomicInteger(1)

    val lists = ConcurrentHashMap<Int, List>()
    var nextListId = AtomicInteger(1)

    val cards = ConcurrentHashMap<Int, Card>()
    var nextCardId = AtomicInteger(1)

    val userBoard = HashMap<Int, kotlin.collections.List<Int>>()

}
