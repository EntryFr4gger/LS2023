async function SafeFetch(endpoint, init) {
    let json
    try {
        let res;
        if (init !== undefined)
            res = await fetch(endpoint, init);
        else
            res = await fetch(endpoint);

        json = await res.json();

        if (res.ok)
            return json;
        else
            alert(json.error);

    } catch (err) {
        alert(err);
        throw err;
    }
}

export default SafeFetch;
