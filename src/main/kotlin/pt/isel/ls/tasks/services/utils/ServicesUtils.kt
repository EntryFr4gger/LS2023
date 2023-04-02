package pt.isel.ls.tasks.services.utils

import pt.isel.ls.tasks.db.TaskData
import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.domain.Board
import pt.isel.ls.tasks.domain.Card
import pt.isel.ls.tasks.domain.List
import pt.isel.ls.tasks.domain.Token
import pt.isel.ls.tasks.domain.User
import pt.isel.ls.tasks.services.errors.ServicesError

open class ServicesUtils(open val source: TaskData) {

    /**
     *
     * Validate Tables
     *
     * */

    /**
     * Validates if email is new.
     *
     * @param conn connection to database.
     * @param email the user's unique email.
     *
     * @throws ServicesError.AlreadyExistsException if email is in use.
     * */
    fun isUserNewEmail(conn: TransactionManager, email: String) {
        if (source.users.hasUserEmail(conn, email)) {
            throw ServicesError.AlreadyExistsException("User email is already in use")
        }
    }

    /**
     * Validates if email is new.
     *
     * @param conn connection to database.
     * @param name unique name for the board.
     *
     * @throws ServicesError.AlreadyExistsException if name is in use.
     * */
    fun isBoardNewName(conn: TransactionManager, name: String) {
        if (source.boards.hasBoardName(conn, name)) {
            throw ServicesError.AlreadyExistsException("Board name is already in use")
        }
    }

    /**
     * Verifys if User exists.
     *
     * @param conn connection to database.
     * @param userId user unique identifier.
     *
     * @throws ServicesError.InvalidArgumentException if id doesn't exists.
     * */
    fun hasUser(conn: TransactionManager, userId: Int) {
        if (!source.users.hasUser(conn, userId)) {
            throw ServicesError.InvalidArgumentException("User id doesn't exists")
        }
    }

    /**
     * Verifys if Board exists.
     *
     * @param conn connection to database.
     * @param boardId board unique identifier.
     *
     * @throws ServicesError.InvalidArgumentException if id doesn't exists.
     * */
    fun hasBoard(conn: TransactionManager, boardId: Int) {
        if (!source.boards.hasBoard(conn, boardId)) {
            throw ServicesError.InvalidArgumentException("Board id doesn't exists")
        }
    }

    /**
     * Verifys if List exists.
     *
     * @param conn connection to database.
     * @param listId list unique identifier.
     *
     * @throws ServicesError.InvalidArgumentException if id doesn't exists.
     * */
    fun hasList(conn: TransactionManager, listId: Int) {
        if (!source.lists.hasList(conn, listId)) {
            throw ServicesError.InvalidArgumentException("List id doesnt exists")
        }
    }

    /**
     * Verifys if Card exists.
     *
     * @param conn connection to database.
     * @param cardId card unique identifier.
     *
     * @throws ServicesError.InvalidArgumentException if id doesn't exists.
     * */
    fun hasCard(conn: TransactionManager, cardId: Int) {
        if (!source.cards.hasCard(conn, cardId)) {
            throw ServicesError.InvalidArgumentException("Card id doesnt exists")
        }
    }

    /**
     * Verifys if User is authenticated.
     *
     * @param conn connection to database.
     * @param requestId request user unique identifier.
     *
     * @throws ServicesError.AuthenticationException if user inst authenticated.
     * */
    fun authentication(conn: TransactionManager, requestId: Int) {
        if (!source.users.hasUser(conn, requestId)) {
            throw ServicesError.AuthenticationException("Invalid User Request")
        }
    }

    /**
     * Verifys if User as authorization to access Board.
     *
     * @param conn connection to database.
     * @param boardId board unique identifier.
     * @param requestId request user unique identifier.
     *
     * @throws ServicesError.AuthorizationException if user inst authorized.
     * */
    fun authorizationBoard(conn: TransactionManager, boardId: Int, requestId: Int) {
        if (!source.users.validateResquestBoard(conn, boardId, requestId)) {
            throw ServicesError.AuthorizationException("You are not authorized to access this Board")
        }
    }

    /**
     * Verifys if User as authorization to access List.
     *
     * @param conn connection to database.
     * @param listId list unique identifier.
     * @param requestId request user unique identifier.
     *
     * @throws ServicesError.AuthorizationException if user inst authorized.
     * */
    fun authorizationList(conn: TransactionManager, listId: Int, requestId: Int) {
        if (!source.users.validateResquestList(conn, listId, requestId)) {
            throw ServicesError.AuthorizationException("You are not authorized to access this List")
        }
    }

    /**
     * Verifys if User as authorization to access Card.
     *
     * @param conn connection to database.
     * @param cardId card unique identifier.
     * @param requestId request user unique identifier.
     *
     * @throws ServicesError.AuthorizationException if user inst authorized.
     * */
    fun authorizationCard(conn: TransactionManager, cardId: Int, requestId: Int) {
        if (!source.users.validateResquestList(conn, cardId, requestId)) {
            throw ServicesError.AuthorizationException("You are not authorized to access this Card")
        }
    }

    /**
     *
     * Validate parameters
     *
     * */

    /**
     * Verifys if user id is valid.
     *
     * @param id user unique identifier.
     * */
    fun isValidUserId(id: Int) {
        if (isValidId(id)) {
            throw ServicesError.InvalidArgumentException("User id Incorrect")
        }
    }

    /**
     * Verifys if board id is valid.
     *
     * @param id board unique identifier.
     * */
    fun isValidBoardId(id: Int) {
        if (isValidId(id)) {
            throw ServicesError.InvalidArgumentException("Board id Incorrect")
        }
    }

    /**
     * Verifys if list id is valid.
     *
     * @param id list unique identifier.
     * */
    fun isValidListId(id: Int) {
        if (isValidId(id)) {
            throw ServicesError.InvalidArgumentException("List id Incorrect")
        }
    }

    /**
     * Verifys if card id is valid.
     *
     * @param id card unique identifier.
     * */
    fun isValidCardId(id: Int) {
        if (isValidId(id)) {
            throw ServicesError.InvalidArgumentException("Card id Incorrect")
        }
    }

    /**
     * Verifys if user name is valid.
     *
     * @param name the user's name.
     * */
    fun isValidUserName(name: String) {
        if (!User.isValidName(name)) {
            throw ServicesError.InvalidArgumentException("User name with wrong length")
        }
    }

    /**
     * Verifys if user email is valid.
     *
     * @param email the user's unique email.
     * */
    fun isValidUserEmail(email: String) {
        if (!User.isValidEmail(email)) {
            throw ServicesError.InvalidArgumentException("User email with wrong length")
        }
    }

    /**
     * Verifys if board name is valid.
     *
     * @param name unique name for the board.
     * */
    fun isValidBoardName(name: String) {
        if (!Board.isValidName(name)) {
            throw ServicesError.InvalidArgumentException("Board name with wrong length")
        }
    }

    /**
     * Verifys if board description is valid.
     *
     * @param description board description.
     * */
    fun isValidBoardDescription(description: String) {
        if (!Board.isValidDescription(description)) {
            throw ServicesError.InvalidArgumentException("Board description with wrong length")
        }
    }

    /**
     * Verifys if card name is valid.
     *
     * @param name the task name.
     * */
    fun isValidCardName(name: String) {
        if (!Card.isValidName(name)) {
            throw ServicesError.InvalidArgumentException("Card name with wrong length")
        }
    }

    /**
     * Verifys if card description is valid.
     *
     * @param description the task description.
     * */
    fun isValidCardDescription(description: String) {
        if (!Card.isValidDescription(description)) {
            throw ServicesError.InvalidArgumentException("Card description with wrong length")
        }
    }

    /**
     * Verifys if list name is valid.
     *
     * @param name list name.
     * */
    fun isValidListName(name: String) {
        if (!List.isValidName(name)) {
            throw ServicesError.InvalidArgumentException("List name with wrong length")
        }
    }

    /**
     * Verifys if token is valid.
     *
     * @param token user token.
     * */
    fun isValidToken(token: String) {
        if (!Token.isValidToken(token)) {
            throw ServicesError.InvalidArgumentException("Token does not obey rules")
        }
    }

    /**
     * Verifys if token is valid.
     *
     * @param token user token.
     * */
    fun isValidBearerToken(token: String) {
        if (!Token.isValidBearerToken(token)) {
            throw ServicesError.InvalidArgumentException("Token does not obey rules")
        }
    }
}

/**
 * Verifys if id is valid.
 *
 * @param id unique identifier.
 *
 * @return true if is valid, false otherwise.
 * */
fun isValidId(id: Int) = id <= 0
