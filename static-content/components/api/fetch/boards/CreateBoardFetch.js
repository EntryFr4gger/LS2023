import {getUserToken} from "../../../utils/get-token.js";

export async function CreateBoardFetch(name, description) {
    return await fetch(`boards/`, {
        method: "POST",
        headers: {Authorization: getUserToken()},
        body: JSON.stringify({name, description})
    });
}