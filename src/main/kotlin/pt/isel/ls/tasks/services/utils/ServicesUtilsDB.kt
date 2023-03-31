package pt.isel.ls.tasks.services.utils

import pt.isel.ls.tasks.db.TaskData
import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.services.errors.ServicesError

class ServicesUtilsDB(val source: TaskData) {

    fun isUserNewEmail(conn: TransactionManager, email: String) {
        if (!source.users.isNewEmail(conn, email)) {
            throw ServicesError.AlreadyExistsException("User email is already in use")
        }
    }

    fun isBoardNewName(conn: TransactionManager, name: String) {
        if (!source.boards.isNewName(conn, name)) {
            throw ServicesError.AlreadyExistsException("Board name is already in use")
        }
    }

    fun hasUser(conn: TransactionManager, userId: Int) {
        if (!source.users.hasUser(conn, userId)) {
            throw ServicesError.InvalidArgumentException("User id doesn't exists")
        }
    }

    fun hasBoard(conn: TransactionManager, boardId: Int) {
        if (!source.boards.hasBoard(conn, boardId)) {
            throw ServicesError.InvalidArgumentException("Board id doesn't exists")
        }
    }

    fun hasList(conn: TransactionManager, listId: Int) {
        if (!source.lists.hasList(conn, listId)) {
            throw ServicesError.InvalidArgumentException("List id doesnt exists")
        }
    }

    fun hasCard(conn: TransactionManager, cardId: Int) {
        if (!source.cards.hasCard(conn, cardId)) {
            throw ServicesError.InvalidArgumentException("Card id doesnt exists")
        }
    }

    fun auth(conn: TransactionManager, token: String): Int {
        if (!source.tokens.hasToken(conn, token)) {
            throw ServicesError.InvalidArgumentException("Token doesn't exists")
        }
        return source.tokens.getUserID(conn, token)
    }
}
