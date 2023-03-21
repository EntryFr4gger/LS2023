package pt.isel.ls.tasks.database.modules.lists

interface ListsTestDB {

    /**
     * Creates a new list on a board.
     * */
    fun `List is created correctly and with right identifier`()

    /**
     * Get the lists of a board.
     * */
    fun `Gets the correct lists of a board`()

    fun `Throws an error for a nonexistent lists`()

    /**
     * Get detailed information of a list.
     * */

    fun `Get the correct list`()

    fun `Throws an error for a nonexistent list `()
}
