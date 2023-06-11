package pt.isel.ls.tasks.services.utils

import pt.isel.ls.tasks.db.TaskData
import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.domain.Board
import pt.isel.ls.tasks.domain.Card
import pt.isel.ls.tasks.domain.List
import pt.isel.ls.tasks.domain.Token
import pt.isel.ls.tasks.domain.User
import pt.isel.ls.tasks.services.errors.ServicesError
import java.security.MessageDigest

open class ServicesUtils(open val source: TaskData) {

    /**
     *
     * Validate Tables
     *
     * */

    /**
     * Validates if user email is new.
     *
     * @param conn connection to a database.
     * @param email the user's unique email.
     *
     * @throws ServicesError.AlreadyExistsException if email is in use.
     * */
    fun isUserNewEmail(conn: TransactionManager, email: String) {
        if (source.users.hasUserEmail(conn, email)) {
            throw ServicesError.AlreadyExistsException("User email($email) is already in use")
        }
    }

    /**
     * Validates if board name is new.
     *
     * @param conn connection to a database.
     * @param name unique name for the board.
     *
     * @throws ServicesError.AlreadyExistsException if name is in use.
     * */
    fun isBoardNewName(conn: TransactionManager, name: String) {
        if (source.boards.hasBoardName(conn, name)) {
            throw ServicesError.AlreadyExistsException("Board name($name) is already in use")
        }
    }

    /**
     * Verifys if User exists.
     *
     * @param conn connection to a database.
     * @param userId user unique identifier.
     *
     * @throws ServicesError.InvalidArgumentException if id doesn't exists.
     * */
    private fun hasUser(conn: TransactionManager, userId: Int) {
        if (!source.users.hasUser(conn, userId)) {
            throw ServicesError.InvalidArgumentException("User id($userId) doesn't exists")
        }
    }

    /**
     * Verifys if Board exists.
     *
     * @param conn connection to a database.
     * @param boardId board unique identifier.
     *
     * @throws ServicesError.InvalidArgumentException if id doesn't exists.
     * */
    private fun hasBoard(conn: TransactionManager, boardId: Int) {
        if (!source.boards.hasBoard(conn, boardId)) {
            throw ServicesError.InvalidArgumentException("Board id($boardId) doesn't exists")
        }
    }

    /**
     * Verifys if List exists.
     *
     * @param conn connection to a database.
     * @param listId list unique identifier.
     *
     * @throws ServicesError.InvalidArgumentException if id doesn't exists.
     * */
    private fun hasList(conn: TransactionManager, listId: Int) {
        if (!source.lists.hasList(conn, listId)) {
            throw ServicesError.InvalidArgumentException("List id($listId) doesn't exists")
        }
    }

    /**
     * Verifys if Card exists.
     *
     * @param conn connection to a database.
     * @param cardId card unique identifier.
     *
     * @throws ServicesError.InvalidArgumentException if id doesn't exists.
     * */
    private fun hasCard(conn: TransactionManager, cardId: Int) {
        if (!source.cards.hasCard(conn, cardId)) {
            throw ServicesError.InvalidArgumentException("Card id($cardId) doesn't exists")
        }
    }

    /**
     * Verifys if User is authenticated.
     *
     * @param conn connection to a database.
     * @param requestId request user unique identifier.
     *
     * @throws ServicesError.AuthenticationException if user ins't authenticated.
     * */
    fun authentication(conn: TransactionManager, requestId: Int) {
        if (!source.users.hasUser(conn, requestId)) {
            throw ServicesError.AuthenticationException("Invalid User Request($requestId)")
        }
    }

    /**
     * Verifys if User as authorization to access Board.
     *
     * @param conn connection to a database.
     * @param boardId board unique identifier.
     * @param requestId request user unique identifier.
     *
     * @throws ServicesError.AuthorizationException if user ins't authorized.
     * */
    fun authorizationBoard(conn: TransactionManager, boardId: Int, requestId: Int) {
        hasBoard(conn, boardId)
        hasUser(conn, requestId)
        if (!source.users.validateResquestBoard(conn, boardId, requestId)) {
            throw ServicesError
                .AuthorizationException("You are not authorized to access this Board($boardId) with requestId:$requestId")
        }
    }

    /**
     * Verifys if User as authorization to access List.
     *
     * @param conn connection to a database.
     * @param listId list unique identifier.
     * @param requestId request user unique identifier.
     *
     * @throws ServicesError.AuthorizationException if user ins't authorized.
     * */
    fun authorizationList(conn: TransactionManager, listId: Int, requestId: Int) {
        hasList(conn, listId)
        hasUser(conn, requestId)
        if (!source.users.validateResquestList(conn, listId, requestId)) {
            throw ServicesError
                .AuthorizationException("You are not authorized to access this List($listId) with requestId:$requestId")
        }
    }

    /**
     * Verifys if User as authorization to access Card.
     *
     * @param conn connection to a database.
     * @param cardId card unique identifier.
     * @param requestId request user unique identifier.
     *
     * @throws ServicesError.AuthorizationException if user ins't authorized.
     * */
    fun authorizationCard(conn: TransactionManager, cardId: Int, requestId: Int) {
        hasCard(conn, cardId)
        hasUser(conn, requestId)
        if (!source.users.validateResquestCard(conn, cardId, requestId)) {
            throw ServicesError
                .AuthorizationException("You are not authorized to access this Card($cardId) with requestId:$requestId")
        }
    }

    /**
     *
     * Organize
     *
     * */

    /**
     * Organize all cards after change the index of the disered card.
     *
     * @param conn connection to a database.
     * @param listId list unique identifier.
     * @param cardId card unique identifier.
     * @param cix desired index.
     * */
    fun organizeListCards(conn: TransactionManager, listId: Int, cardId: Int, cix: Int) {
        updateCardsList(
            conn,
            source.lists.getAllCards(conn, listId, 0, Int.MAX_VALUE)
                .map {
                    when {
                        it.id == cardId -> it.copy(cix = cix)
                        it.cix == cix -> it.copy(cix = cix + 1)
                        else -> it
                    }
                }
        )
    }

    /**
     * Reorganize all cards.
     *
     * @param conn connection to a database.
     * @param listId list unique identifier.
     * */
    fun organizeCards(conn: TransactionManager, listId: Int) =
        updateCardsList(conn, source.lists.getAllCards(conn, listId, 0, Int.MAX_VALUE))

    /**
     * Sorts the list of cards, reorganize for index and update the database.
     *
     * @param conn connection to a database.
     * @param cardsList list of Cards.
     * */
    private fun updateCardsList(conn: TransactionManager, cardsList: kotlin.collections.List<Card>) {
        cardsList.sortedBy { it.cix }
            .mapIndexed { index, card -> card.copy(cix = index + 1) }
            .forEach {
                source.cards.organizeCardSeq(conn, it.id, it.cix)
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
     *
     * @throws ServicesError.InvalidArgumentException if id isn't correct.
     * */
    fun isValidUserId(id: Int) {
        if (!isValidId(id)) {
            throw ServicesError.InvalidArgumentException("User id Incorrect($id)")
        }
    }

    /**
     * Verifys if board id is valid.
     *
     * @param id board unique identifier.
     *
     * @throws ServicesError.InvalidArgumentException if id isn't correct.
     * */
    fun isValidBoardId(id: Int) {
        if (!isValidId(id)) {
            throw ServicesError.InvalidArgumentException("Board id Incorrect($id)")
        }
    }

    /**
     * Verifys if list id is valid.
     *
     * @param id list unique identifier.
     *
     * @throws ServicesError.InvalidArgumentException if id isn't correct.
     * */
    fun isValidListId(id: Int) {
        if (!isValidId(id)) {
            throw ServicesError.InvalidArgumentException("List id Incorrect($id)")
        }
    }

    /**
     * Verifys if the given fields are valid for the board details.
     *
     * @param id card unique identifier.
     *
     * @throws ServicesError.InvalidArgumentException if id isn't correct.
     * */
    fun isValidFieldsBoardDetails(fields: kotlin.collections.List<String>) {
        val validFields = listOf("lists") // "id", "name", "description"
        if (!isValidFields(validFields, fields)) {
            throw ServicesError.InvalidArgumentException("Given fields were invalid ${fields.joinToString(" ")}")
        }
    }

    /**
     * Verifys if the given fields are valid for the List details.
     *
     * @param id card unique identifier.
     *
     * @throws ServicesError.InvalidArgumentException if id isn't correct.
     * */
    fun isValidFieldsListDetails(fields: kotlin.collections.List<String>) {
        val validFields = listOf("cards") // "id", "name", "boardId",
        if (!isValidFields(validFields, fields)) {
            throw ServicesError.InvalidArgumentException("Given fields were invalid ${fields.joinToString(" ")}")
        }
    }

    /**
     * Verifys if the given fields are valid for the Card details.
     *
     * @param id card unique identifier.
     *
     * @throws ServicesError.InvalidArgumentException if id isn't correct.
     * */
    fun isValidFieldsCardDetails(fields: kotlin.collections.List<String>) {
        val validFields = listOf("id", "name", "description", "dueDate", "cix", "boardId", "listId")
        if (!isValidFields(validFields, fields)) {
            throw ServicesError.InvalidArgumentException("Given fields were invalid ${fields.joinToString(" ")}")
        }
    }

    /**
     * Given fields, test if they are contained withthin the valid fields
     *
     * @return true if valid
     */
    private fun isValidFields(
        validFields: kotlin.collections.List<String>,
        fields: kotlin.collections.List<String>
    ): Boolean {
        return validFields.containsAll(fields)
    }

    /**
     * Verifys if card id is valid.
     *
     * @param id card unique identifier.
     *
     * @throws ServicesError.InvalidArgumentException if id isn't correct.
     * */
    fun isValidCardId(id: Int) {
        if (!isValidId(id)) {
            throw ServicesError.InvalidArgumentException("Card id Incorrect($id)")
        }
    }

    /**
     * Verifys if cix is valid.
     *
     * @param cix a idx.
     *
     * @throws ServicesError.InvalidArgumentException if idx isn't correct.
     * */
    fun isValidCardCix(cix: Int) {
        if (!isValidId(cix)) {
            throw ServicesError.InvalidArgumentException("Card idx Incorrect($cix)")
        }
    }

    /**
     * Verifys if skip and limit is valid.
     *
     * @param skip skip database tables.
     * @param limit limit database tables.
     *
     * @throws ServicesError.InvalidArgumentException skip isn't correct.
     * @throws ServicesError.InvalidArgumentException limit isn't correct.
     * */
    fun isValidSkipAndLimit(skip: Int, limit: Int) {
        if (skip < 0) {
            throw ServicesError.InvalidArgumentException("Skip($skip) is invalid")
        }
        if (limit < 0) {
            throw ServicesError.InvalidArgumentException("Limit($limit) is invalid")
        }
    }

    /**
     * Verifys if user name is valid.
     *
     * @param name the user's name.
     *
     * @throws ServicesError.InvalidArgumentException name is the worng length.
     * */
    fun isValidUserName(name: String) {
        if (!User.isValidName(name)) {
            throw ServicesError.InvalidArgumentException("User name with wrong length($name)")
        }
    }

    /**
     * Verifys if user email is valid.
     *
     * @param email the user's unique email.
     *
     * @throws ServicesError.InvalidArgumentException email is the worng length.
     * */
    fun isValidUserEmail(email: String) {
        if (!User.isValidEmail(email)) {
            throw ServicesError.InvalidArgumentException("User email with wrong length($email)")
        }
    }

    /**
     * Verifys if board name is valid.
     *
     * @param name unique name for the board.
     *
     * @throws ServicesError.InvalidArgumentException name is the worng length.
     * */
    fun isValidBoardName(name: String) {
        if (!Board.isValidName(name)) {
            throw ServicesError.InvalidArgumentException("Board name with wrong length($name)")
        }
    }

    /**
     * Verifys if board description is valid.
     *
     * @param description board description.
     *
     * @throws ServicesError.InvalidArgumentException description is the worng length.
     * */
    fun isValidBoardDescription(description: String) {
        if (!Board.isValidDescription(description)) {
            throw ServicesError.InvalidArgumentException("Board description with wrong length($description)")
        }
    }

    /**
     * Verifys if card name is valid.
     *
     * @param name the task name.
     *
     * @throws ServicesError.InvalidArgumentException name is the worng length.
     * */
    fun isValidCardName(name: String) {
        if (!Card.isValidName(name)) {
            throw ServicesError.InvalidArgumentException("Card name with wrong length($name)")
        }
    }

    /**
     * Verifys if card description is valid.
     *
     * @param description the task description.
     *
     * @throws ServicesError.InvalidArgumentException description is the worng length.
     * */
    fun isValidCardDescription(description: String) {
        if (!Card.isValidDescription(description)) {
            throw ServicesError.InvalidArgumentException("Card description with wrong length($description)")
        }
    }

    /**
     * Verifys if list name is valid.
     *
     * @param name list name.
     *
     * @throws ServicesError.InvalidArgumentException name is the worng length.
     * */
    fun isValidListName(name: String) {
        if (!List.isValidName(name)) {
            throw ServicesError.InvalidArgumentException("List name with wrong length($name)")
        }
    }

    /**
     * Verifys if token is valid.
     *
     * @param token user token.
     *
     * @throws ServicesError.InvalidArgumentException token isn't correct.
     * */
    fun isValidToken(token: String) {
        if (!Token.isValidToken(token)) {
            throw ServicesError.InvalidArgumentException("Token does not obey rules($token)")
        }
    }

    /**
     * Verifys if token is valid.
     *
     * @param token user token.
     *
     * @throws ServicesError.InvalidArgumentException token isn't correct.
     * */
    fun isValidBearerToken(token: String) {
        if (!Token.isValidBearerToken(token)) {
            throw ServicesError.InvalidArgumentException("Token does not obey rules($token)")
        }
    }

    /**
     * Wrapper function to obfuscate the hash used,
     * can be changed if need be
     */
    fun hashPassword(password: String) = sha256Hash(password)

    /**
     * Hashing method used for the password Encryption
     *
     * @param input, takes the clear text password
     *
     * @return a String with the hashed password
     */
    private fun sha256Hash(input: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(input.toByteArray())
        return bytesToHex(digest)
    }

    /**
     * Converts a Byte Array to a string
     *
     * @param bytes, Byte array
     *
     * @return a String
     */
    private fun bytesToHex(bytes: ByteArray): String {
        val hexChars = "0123456789ABCDEF"
        val result = StringBuilder(bytes.size * 2)
        for (byte in bytes) {
            val index = byte.toInt() and 0xFF
            result.append(hexChars[index ushr 4])
            result.append(hexChars[index and 0x0F])
        }
        return result.toString()
    }
}

/**
 * Verifys if id is valid.
 *
 * @param id unique identifier.
 *
 * @return true if is valid, false otherwise.
 * */
fun isValidId(id: Int) = id > 0
