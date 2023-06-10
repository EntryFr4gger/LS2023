async function SafeFetch(endpoint, init) {
    let json
    try {
        const res = await fetch(endpoint, init);
        json = await res.json();

        if (res.ok)
            return json;
        else
            alert(json.error);

    } catch (err) {
        /*if (isAppError(err))
            throw err;*/

        //throw new LogError(err);
        alert(err);
    }
}

export default SafeFetch;
