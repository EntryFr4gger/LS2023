package pt.isel.ls.tasks.services.utils

import pt.isel.ls.tasks.domain.*
import pt.isel.ls.tasks.domain.List
import pt.isel.ls.tasks.services.errors.ServicesError

fun isValidId(id: Int) = id <= 0

fun isValidUserId(id: Int) {
    if (isValidId(id)) {
        throw ServicesError.InvalidArgumentException("User id Incorrect")
    }
}

fun isValidBoardId(id: Int) {
    if (isValidId(id)) {
        throw ServicesError.InvalidArgumentException("Board id Incorrect")
    }
}

fun isValidListId(id: Int) {
    if (isValidId(id)) {
        throw ServicesError.InvalidArgumentException("List id Incorrect")
    }
}

fun isValidCardId(id: Int) {
    if (isValidId(id)) {
        throw ServicesError.InvalidArgumentException("Card id Incorrect")
    }
}

fun isValidUserName(name: String) {
    if (!User.isValidName(name)) {
        throw ServicesError.InvalidArgumentException("User name with wrong length")
    }
}

fun isValidUserEmail(email: String) {
    if (!User.isValidEmail(email)) {
        throw ServicesError.InvalidArgumentException("User email with wrong length")
    }
}

fun isValidBoardName(name: String) {
    if (!Board.isValidName(name)) {
        throw ServicesError.InvalidArgumentException("Board name with wrong length")
    }
}

fun isValidBoardDescription(description: String) {
    if (!Board.isValidDescription(description)) {
        throw ServicesError.InvalidArgumentException("Board description with wrong length")
    }
}

fun isValidCardName(name: String) {
    if (!Card.isValidName(name)) {
        throw ServicesError.InvalidArgumentException("Card name with wrong length")
    }
}

fun isValidCardDescription(description: String) {
    if (!Card.isValidDescription(description)) {
        throw ServicesError.InvalidArgumentException("Card description with wrong length")
    }
}

fun isValidListName(name: String) {
    if (!List.isValidName(name)) {
        throw ServicesError.InvalidArgumentException("List name with wrong length")
    }
}

fun isValidToken(token: String) {
    if (!Token.isValidToken(token)) {
        throw ServicesError.InvalidArgumentException("Token does not obey rules")
    }
}
