export const userIdStorage = "userId"

export function getUser() {
    return localStorage.getItem(userIdStorage)
}
