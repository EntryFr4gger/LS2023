export async function CreateUserFetch(name, email, password) {
    return await fetch(`http://localhost:9000/users/`, {
        method: "POST",
        body: JSON.stringify({name, email, password})
    });
}
