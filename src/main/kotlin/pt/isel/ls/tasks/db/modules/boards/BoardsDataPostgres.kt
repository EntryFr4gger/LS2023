package pt.isel.ls.tasks.db.modules.boards

import pt.isel.ls.tasks.domain.Board
import pt.isel.ls.tasks.domain.User
import java.sql.Connection
import java.sql.Statement

class BoardsDataPostgres(): BoardsDB {
    override fun createNewBoard(conn: Connection, name: String, description: String): Int {
        val res = conn.prepareStatement(
            "INSERT INTO boards(name, description) VALUES (?, ?)",
            Statement.RETURN_GENERATED_KEYS
        )
        res.setString(1, name)
        res.setString(2, description)

        if(res.executeUpdate() == 0) throw Error("Board Create Failed(sql)")

        res.generatedKeys.also {
            return if (it.next()) it.getInt(1) else -1
        }
    }

    override fun addUserToBoard(conn: Connection, userId: Int, boardId: Int): Int {
        val res = conn.prepareStatement(
            "INSERT INTO user_board(user_id, board_id) VALUES (?, ?)",
            Statement.RETURN_GENERATED_KEYS
        )
        res.setInt(1, userId)
        res.setInt(2, boardId)

        if(res.executeUpdate() == 0) throw Error("Couldnt add user to board(sql)")

        res.generatedKeys.also {
            return if (it.next()) it.getInt(1) else -1
        }
    }

    //Melhorar
    override fun getUserBoards(conn: Connection, userId: Int): List<Board> {
        val prp = conn.prepareStatement(
            "SELECT * FROM boards JOIN user_board ON id = board_id WHERE user_id = ?",
        )
        prp.setInt(1, userId)

        val res = prp.executeQuery()

        var list  = emptyList<Board>()
        while (res.next()){
            list += Board(
                res.getInt(1),
                res.getString(2),
                res.getString(3)
            )
        }
        if (list.isNotEmpty())
            return list
        else
            throw Error("No boards")
    }

    override fun getBoardDetails(conn: Connection, boardId: Int): Board {
        val prp = conn.prepareStatement(
            "SELECT * FROM boards WHERE id = ?",
        )
        prp.setInt(1, boardId)

        val res = prp.executeQuery()
        if (res.next())
            return Board(
                res.getInt(1),
                res.getString(2),
                res.getString(3)
            )
        else throw Error("No board")
    }
}