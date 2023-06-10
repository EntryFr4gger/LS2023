import {getUserToken} from "../../../utils/get-token.js";

export async function AddUserToBoardFetch(boardId, userId) {
    return await fetch(`boards/${boardId}/users`, {
        method: "POST",
        headers: {Authorization: getUserToken()},
        body: JSON.stringify({id: userId})
    });
}