import {getUserToken} from "../../../utils/get-token.js";
import {getUser} from "../../../utils/get-user.js";

export async function GetAllUsers(boardId) {

    return await fetch(`users/${getUser()}`, {
        method: "POST",
        headers: {Authorization: getUserToken()},
        body: JSON.stringify({id: boardId})
    });
}