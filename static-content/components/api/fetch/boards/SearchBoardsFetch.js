import {getUserToken} from "../../../utils/get-token.js";

export async function SearchBoardsFetch(name, skip, limit) {
    const params = new URLSearchParams()
    params.set("name", name)
    params.set("skip", skip)
    params.set("limit", limit)
    return await fetch(`boards/?${params}`, {
        headers: {Authorization: getUserToken()}
    });
}