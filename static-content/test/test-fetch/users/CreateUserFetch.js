export async function CreateUserFetch(name, email, password) {
    return await fetch(`users/`, {
        method: "POST",
        body: JSON.stringify({name, email, password})
    });
}