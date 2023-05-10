export async function GetUserFetch(userId) {
    return await fetch(`users/${userId}`);
}
