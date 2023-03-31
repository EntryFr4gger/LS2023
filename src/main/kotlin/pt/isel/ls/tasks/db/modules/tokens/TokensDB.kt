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
     * Verify if token exists.
     *
     * @param conn connection to database.
     * @param token user unique token.
     *
     * @return true if exists or false if it does not exist.
     */
    fun hasToken(conn: TransactionManager, token: String): Boolean
}
