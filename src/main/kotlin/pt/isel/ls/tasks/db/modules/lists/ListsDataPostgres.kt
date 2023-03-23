package pt.isel.ls.tasks.db.modules.lists

import pt.isel.ls.tasks.domain.List as _List
import java.sql.Connection
import java.sql.Statement

class ListsDataPostgres: ListsDB {
    override fun createList(conn: Connection, name: String, boardId: Int): Int {
        val obj = conn.prepareStatement(
            "INSERT INTO lists(name, board_id) VALUES (?, ?)",
            Statement.RETURN_GENERATED_KEYS
        )
        obj.setString(1, name)
        obj.setInt(2, boardId)

        if(obj.executeUpdate() == 0) throw Error("List Create Failed(sql)")

        obj.generatedKeys.also {
            return if (it.next()) it.getInt(1) else -1
        }
    }

    override fun getAllLists(conn: Connection, boardId: Int): List<_List> {
        val obj = conn.prepareStatement(
            "SELECT * FROM lists WHERE board_id = ?",
        )
        obj.setInt(1, boardId)

        val res = obj.executeQuery()

        val lists = mutableListOf<_List>()
        while (res.next()){
            lists += _List(
                res.getInt(1),
                res.getString(2),
                res.getInt(3)
            )
        }
        return lists
    }

    override fun getListDetails(conn: Connection, listId: Int): _List {
        val obj = conn.prepareStatement(
            "SELECT * FROM lists WHERE id = ?",
        )
        obj.setInt(1, listId)

        val res = obj.executeQuery()
        if (res.next())
            return _List(
                res.getInt(1),
                res.getString(2),
                res.getInt(3)
            )
        else throw Error("No List")
    }

}