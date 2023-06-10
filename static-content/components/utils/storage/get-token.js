/**
 * UserTokenStorage is a constant that represents the key for storing the user token in sessionStorage.
 */
export const userTokenStorage = "userToken"

/**
 * GetUserToken is a function that retrieves the user token from sessionStorage.
 *
 * @returns {string|null} The user token if it exists, or null if it doesn't.
 */
export function getUserToken() {
    return sessionStorage.getItem("userToken")
}
