/**
 * SafeFetch - Performs a safe HTTP request using the Fetch API
 *
 * @param {string} endpoint - The URL or endpoint to fetch data from
 * @param {Object} init - (Optional) The configuration options for the request (e.g., method, headers, body, etc.)
 *
 * @returns {Promise} - A Promise that resolves to the JSON response if the request is successful
 * @throws {Error} - If an error occurs during the request or if the response is not successful
 */
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
