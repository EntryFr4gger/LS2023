package pt.isel.ls.tasks.db.dataStorage

import pt.isel.ls.tasks.domain.Board
import pt.isel.ls.tasks.domain.Card
import pt.isel.ls.tasks.domain.Token
import pt.isel.ls.tasks.domain.User
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger
import pt.isel.ls.tasks.domain.List as _List


/**
 * Datamem Storage.
 * */
class TasksDataStorage {
    val users = ConcurrentHashMap<Int, User>()
    var nextUserId = AtomicInteger(1)

    val boards = ConcurrentHashMap<Int, Board>()
    var nextBoardId = AtomicInteger(1)

    val lists = ConcurrentHashMap<Int, _List>()
    var nextListId = AtomicInteger(1)

    val cards = ConcurrentHashMap<Int, Card>()
    var nextCardId = AtomicInteger(1)

    val tokens = ConcurrentHashMap<String, Token>()

    val userBoard = ConcurrentHashMap<Int, List<Int>>()
}
