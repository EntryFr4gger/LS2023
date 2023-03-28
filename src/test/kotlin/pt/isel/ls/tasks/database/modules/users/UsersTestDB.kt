package pt.isel.ls.tasks.database.modules.users

interface UsersTestDB {

    /**
     * Create a new user.
     * */
    fun `User is created correctly and with right identifier`()

    fun `Throws an error if email is already in use`()

    /**
     * Get the details of a user.
     */
    fun `Gets the correct user`()

    fun `Throws an error for a nonexistent user `()
}
