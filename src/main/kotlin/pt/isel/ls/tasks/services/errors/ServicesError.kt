package pt.isel.ls.tasks.services.errors

/**
 * Services Error Center.
 * */
open class ServicesError(val error: String?) : Exception(error) {

    /**
     * If user is not authenticated.
     *
     * @param msg message given in the exception
     */
    class AuthenticationException(msg: String? = null) : ServicesError(msg)

    /**
     * If invalid arguments are given.
     *
     * @param msg message given in the exception
     */
    class InvalidArgumentException(msg: String) : ServicesError(msg)

    /**
     * If resource already exists.
     *
     * @param msg message given in the exception
     */
    class AlreadyExistsException(msg: String) : ServicesError(msg)
}
