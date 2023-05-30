export const userTokenStorage = "userToken"

export function getUserToken() {
    return localStorage.getItem("userToken")
}
