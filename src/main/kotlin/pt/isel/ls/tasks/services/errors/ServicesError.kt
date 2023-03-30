package pt.isel.ls.tasks.services.errors

open class ServicesError(val error: String?) : Exception(error) {

    class AuthenticationException(name: String? = null) : ServicesError(name)

    class InvalidArgumentException(name: String) : ServicesError(name)

    class AlreadyExistsException(name: String) : ServicesError(name)
}
