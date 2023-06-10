/**
 * userIdStorage is a constant that represents the key for storing the user ID in sessionStorage.
 */
export const userIdStorage = "userId"

/**
 * getUser is a function that retrieves the user ID from sessionStorage.
 *
 * @returns {string|null} The user ID if it exists, or null if it doesn't.
 */
export function getUser() {
    return localStorage.getItem(userIdStorage)
}
