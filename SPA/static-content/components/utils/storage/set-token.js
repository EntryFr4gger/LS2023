import {userTokenStorage} from "./get-token.js";

/**
 * GetUserToken is a function that retrieves the user token from sessionStorage.
 *
 * @returns {string|null} The user token if it exists, or null if it doesn't.
 */
export function setToken(value) {
    sessionStorage.setItem(userTokenStorage, `Bearer ${value}`)
}
