import {getUserToken} from "../../../utils/get-token.js";

export async function CreateListFetch(name, boardId) {
    return await fetch(`lists/`, {
        method: "POST",
        headers: {Authorization: getUserToken()},
        body: JSON.stringify({name, boardId})
    });
}