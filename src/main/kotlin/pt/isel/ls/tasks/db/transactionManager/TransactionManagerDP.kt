package pt.isel.ls.tasks.db.transactionManager

import java.sql.Connection


class TransactionManagerDP(val connection: Connection) : TransactionManager

fun TransactionManager.connection(): Connection =
    (this as TransactionManagerDP).connection