import SafeFetch from "../../../utils/safe-fetch.js";

/**
 * Executes a fetch request to API.
 * Get the details of a user.
 *
 * @param {Number} userId user unique identifier.
 *
 * @return {Promise} a User.
 * */
export async function GetUserDetailsFetch(userId) {
    return await SafeFetch(`users/${userId}`);
}
