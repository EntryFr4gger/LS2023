package pt.isel.ls.tasks.db.errors

/**
 * When a resource isn't in the database.
 *
 * @param name message given in the exception
 * */
class NotFoundException(name: String? = null) : Exception(name)
