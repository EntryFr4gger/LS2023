package pt.isel.ls.tasks.db.errors

open class DBError(val error: String?) : Exception(error) {

    class NotFoundException(name: String? = null) : DBError(name)
}
