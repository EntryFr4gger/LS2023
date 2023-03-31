package pt.isel.ls.tasks.db.errors

/**
 * When a resource isn't in the database.
 *
 * @param msg message given in the exception
 * */
class NotFoundException(msg: String? = null) : Exception(msg)
