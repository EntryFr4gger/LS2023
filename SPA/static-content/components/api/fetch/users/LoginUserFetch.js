import SafeFetch from "../../../utils/safe-fetch.js";

/**
 * Executes a fetch request to API.
 * Login verifications.
 *
 * @param {String} email the user's unique email.
 * @param {String} password user's passaword
 *
 * @return {Promise} new user unique identifier and token.
 * */
export async function LoginUserFetch(email, password) {
    return await SafeFetch(`users/login`, {
        method: "POST",
        body: JSON.stringify({email, password})
    });
}