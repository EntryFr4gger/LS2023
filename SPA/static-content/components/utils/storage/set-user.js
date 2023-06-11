import {userIdStorage} from "./get-user.js";

/**
 * userStorage is a function that retrieves the user ID from sessionStorage.
 *
 * @returns {string|null} The user ID if it exists, or null if it doesn't.
 */
export function setUser(value) {
    sessionStorage.setItem(userIdStorage, value)
}