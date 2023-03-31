package pt.isel.ls.tasks.db.modules.tokens

import pt.isel.ls.tasks.db.dataStorage.TasksDataStorage
import pt.isel.ls.tasks.db.transactionManager.TransactionManager
import pt.isel.ls.tasks.domain.Token

class TokensDataMem(private val source: TasksDataStorage) : TokensDB {

    init {
        source.tokens["9f1e3d11-8c18-4cd7-93fc-985c4794cfd9"] = Token("9f1e3d11-8c18-4cd7-93fc-985c4794cfd9", 1)
        source.tokens["15f27cdd-c450-4a90-8700-6c439c6080e5"] = Token("15f27cdd-c450-4a90-8700-6c439c6080e5", 2)
        source.tokens["b606bd17-aac8-470e-a539-fe590944b1f5"] = Token("b606bd17-aac8-470e-a539-fe590944b1f5", 3)
        source.tokens["b606bd17-aac8-470e-a539-fe590944b1f0"] = Token("b606bd17-aac8-470e-a539-fe590944b1f0", 4)
    }
    override fun createNewToken(conn: TransactionManager, token: String, userId: Int): String {
        source.tokens[token] = Token(token, userId)
        return token
    }

    override fun getUserID(conn: TransactionManager, token: String): Int {
        val res = source.tokens[token] ?: error("user do not exist")
        return res.userId
    }

    override fun hasToken(conn: TransactionManager, token: String): Boolean =
        source.tokens[token] != null
}
