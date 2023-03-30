package pt.isel.ls.tasks.db.modules.tokens

import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.transactionManager.TransactionManager

class TokensDataMem(private val source: TasksDataStorage) : TokensDB {
    override fun createNewToken(conn: TransactionManager, token: String, userId: Int): String {
        TODO("Not yet implemented")
    }

    override fun getUserID(conn: TransactionManager, token: String): Int {
        TODO("Not yet implemented")
    }

    override fun hasToken(conn: TransactionManager, token: String): Boolean {
        TODO("Not yet implemented")
    }
}
