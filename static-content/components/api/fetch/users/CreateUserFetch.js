/**
 * Executes a fetch request to API.
 * Create a new user.
 *
 * @param {String} name the user's name.
 * @param {String} email the user's unique email.
 * @param {String} password user's password.
 *
 * @return {Promise} new user unique identifier and token.
 * */
export async function CreateUserFetch(name, email, password) {
    return await fetch(`users/`, {
        method: "POST",
        body: JSON.stringify({name, email, password})
    });
}
