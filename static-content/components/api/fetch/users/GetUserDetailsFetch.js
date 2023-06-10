/**
 * Executes a fetch request to API.
 * Get the details of a user.
 *
 * @param {Int} userId user unique identifier.
 *
 * @return {Promise} a User.
 * */
export async function GetUserDetailsFetch(userId) {
    return await fetch(`users/${userId}`);
}
