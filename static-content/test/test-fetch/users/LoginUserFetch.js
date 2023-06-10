export async function LoginUserFetch(email, password) {
    return await fetch(`users/1/login`, {
        method: "POST",
        body: JSON.stringify({email, password})
    });
}