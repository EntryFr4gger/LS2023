export function SessionGetRemove(name) {
    const value = sessionStorage.getItem(name)
    sessionStorage.removeItem(name)
    return value
}