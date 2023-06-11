/**
 * SessionGetRemove is a function that retrieves the value of a specified item from the session storage
 * and removes it from the storage.
 *
 * @param {string} name - The name of the item to retrieve and remove from the session storage.
 * @returns {string} The value of the retrieved item from the session storage.
 */
export function SessionGetRemove(name) {
    const value = sessionStorage.getItem(name)
    sessionStorage.removeItem(name)
    return value
}