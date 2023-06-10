import {getUserToken} from "../../../utils/get-token.js";

export async function CreateCardFetch(name, description, boardId, listId) {
    return await fetch(`cards/`, {
        method: "POST",
        headers: {Authorization: getUserToken()},
        body: JSON.stringify(
            {name, description, boardId, listId}
        )
    });
}