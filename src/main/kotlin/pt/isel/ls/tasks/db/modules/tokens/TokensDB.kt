package pt.isel.ls.tasks.db.modules.tokens

import pt.isel.ls.tasks.db.transactionManager.TransactionManager

interface TokensDB {
    /**
     *
     * */
    fun createNewToken(conn: TransactionManager, token: String, userId: Int): String

    /**
     *
     * */
    fun getUserID(conn: TransactionManager, token: String): Int

    /**
     *
     * */
    fun hasToken(conn: TransactionManager, token: String): Boolean
}
