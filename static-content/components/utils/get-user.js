export const userIdLS = "userId"

export function getUser() {
    return localStorage.getItem(userIdLS)
}
