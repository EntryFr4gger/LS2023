package pt.isel.ls.tasks.db.transactionManager

import java.sql.Connection

/**
 * Postgres connection
 * */
class TransactionManagerDP(val connection: Connection) : TransactionManager

/**
 * Creates an instance of [TransactionManagerDP].
 *
 * @return Connection
 * */
fun TransactionManager.connection(): Connection =
    (this as TransactionManagerDP).connection
