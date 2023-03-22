package pt.isel.ls.tasks.db.modules.lists

import pt.isel.ls.tasks.domain.List as _List
import java.sql.Connection
import java.sql.Statement

class ListsDataPostgres(): ListsDB {
    override fun createList(conn: Connection, name: String, boardId: Int): Int {
        val res = conn.prepareStatement(
            "INSERT INTO lists(name, board_id) VALUES (?, ?)",
            Statement.RETURN_GENERATED_KEYS
        )
        res.setString(1, name)
        res.setInt(2, boardId)

        if(res.executeUpdate() == 0) throw Error("List Create Failed(sql)")

        res.generatedKeys.also {
            return if (it.next()) it.getInt(1) else -1
        }
    }

    override fun getAllLists(conn: Connection, boardId: Int): List<_List> {
        val prp = conn.prepareStatement(
            "SELECT * FROM lists WHERE board_id = ?",
        )
        prp.setInt(1, boardId)

        val res = prp.executeQuery()

        var list  = emptyList<_List>()
        while (res.next()){
            list += _List(
                res.getInt(1),
                res.getString(2),
                res.getInt(3)
            )
        }
        if (list.isNotEmpty())
            return list
        else
            throw Error("No Lists")
    }

    override fun getListDetails(conn: Connection, listId: Int): _List {
        val prp = conn.prepareStatement(
            "SELECT * FROM lists WHERE id = ?",
        )
        prp.setInt(1, listId)

        val res = prp.executeQuery()
        if (res.next())
            return _List(
                res.getInt(1),
                res.getString(2),
                res.getInt(3)
            )
        else throw Error("No List")
    }

}